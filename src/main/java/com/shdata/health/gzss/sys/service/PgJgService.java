package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.common.utils.DateUtils;
import com.shdata.health.gzss.common.dict.DictService;
import com.shdata.health.gzss.common.entity.PgJgXq;
import com.shdata.health.gzss.common.mapper.PgJgXqMapper;
import com.shdata.health.gzss.common.service.PgJgXqService;
import com.shdata.health.gzss.common.utils.UserUtils;
import com.shdata.health.gzss.common.vo.PgJgXqVo;
import com.shdata.health.gzss.sys.entity.PgJg;
import com.shdata.health.gzss.sys.mapper.PgJgMapper;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.bo.PgJgBo;
import com.shdata.health.gzss.sys.vo.bo.XshBoS;
import com.shdata.health.gzss.sys.vo.resp.PgJgDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static cn.hutool.extra.validation.ValidationUtil.validate;


/**
 * 评估_结果  Service服务
 *
 * @author dwt
 * @date 2024-07-25
 */
@Service
@Slf4j
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class PgJgService extends BaseService<PgJgMapper, PgJg> {

    @Autowired
    private PgJgMapper pgJgMapper;

    @Autowired
    private DictService dictService;


    @Autowired
    private PgJgXqMapper pgJgXqMapper;

    @Autowired
    private PgJgXqService pgJgXqService;

    /*@Autowired
    private UserService userService;*/

    /**
     * 保存或更新
     *
     * @param bo 提交参数
     * @return
     */

    @Transactional
    public String saveOrUpdate(PgJgBo bo) {
        String daId = bo.getDaId();
        if (daId == null) {
            throw new ParameterException("daId 不能为空");
        }

        try {
            // 验证输入数据
            validate(bo);
            PgJgVo pgJgVo = bo.getPgJgVo();
            pgJgVo.setDaId(daId);
            Date pgrq=bo.getPgJgVo().getPgrq();


            // 评估ID
            String pgId;
            PgJg po = null;

            // 评估记录去重检查
            if (StrUtil.isNotBlank(pgJgVo.getPgId())) {
                po = getById(pgJgVo.getPgId());
            }

            if (po == null) { // 新增逻辑
                LambdaQueryWrapper<PgJg> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(StrUtil.isNotBlank(pgJgVo.getDaId()), PgJg::getDaId, pgJgVo.getDaId())
                        .eq(StrUtil.isNotBlank(pgJgVo.getGljg()), PgJg::getGljg, pgJgVo.getGljg())
                        .eq(pgJgVo.getGmdjcks() != null, PgJg::getGmdjcks, pgJgVo.getGmdjcks())
                        .eq(pgJgVo.getGmdjcjs() != null, PgJg::getGmdjcjs, pgJgVo.getGmdjcjs())
                        .eq(pgJgVo.getGmdKsjls() != null, PgJg::getGmdKsjls, pgJgVo.getGmdKsjls())
                        .eq(StrUtil.isNotBlank(pgJgVo.getJyxms()), PgJg::getJyxms, pgJgVo.getJyxms())
                        .eq(pgJgVo.getGmdXss() != null, PgJg::getGmdXss, pgJgVo.getGmdXss())
                        .eq(pgJgVo.getXshjcks() != null, PgJg::getXshjcks, pgJgVo.getXshjcks())
                        .eq(pgJgVo.getXshjcjs() != null, PgJg::getXshjcjs, pgJgVo.getXshjcjs())
                        .eq(pgJgVo.getFyxxks() != null, PgJg::getFyxxks, pgJgVo.getFyxxks())
                        .eq(StrUtil.isNotBlank(pgJgVo.getPgysid()), PgJg::getPgysid, pgJgVo.getPgysid())
                        .eq(StrUtil.isNotBlank(pgJgVo.getPgjg()), PgJg::getPgjg, pgJgVo.getPgjg())
                        .eq(pgJgVo.getPgrq() != null, PgJg::getPgrq, pgJgVo.getPgrq())
                        .eq(PgJg::getDelFlag, "0");

                PgJg existingPgJg = pgJgMapper.selectOne(queryWrapper);
                if (existingPgJg != null) {
                    throw new ParameterException("该档案已存在此患者的评估记录，请勿重复提交");
                }

                // 新增评估记录
                po = BeanUtil.toBean(pgJgVo, PgJg.class);
                pgId = IdUtil.objectId();
                po.setPgys(pgJgVo.getPgysidName());
                po.setPgId(pgId);
                po.init();
                save(po);

                // 插入评估详情记录
                List<XshBoS> xshBoos = bo.getXshBoos();
                if (xshBoos != null && !xshBoos.isEmpty()) {
                    List<PgJgXqVo> pgJgXqList = new ArrayList<>();
                    for (XshBoS xshBoo : xshBoos) {
                        if (xshBoo == null || StrUtil.isBlank(xshBoo.getJyxm())) {
                            throw new ParameterException("XshBo 对象或其 jyxm 不能为空");
                        }
                        //优化下面报错的信息
                        if (xshBoo.getXss() == null || xshBoo.getXss() < 0) {
                            throw new ParameterException("XshBo 对象的 XSS 字段不能为 null 或负数");
                        }
                        if (xshBoo.getKsjls() == null || xshBoo.getKsjls() < 0) {
                            throw new ParameterException("XshBo 对象的 KSJLS 字段不能为 null 或负数");
                        }


                        xshBoo.setPgId(pgId);
                        String jyxm = xshBoo.getJyxm();
                        Long xss = xshBoo.getXss();
                        Long ksjls = xshBoo.getKsjls();


                        if (!CollectionUtil.isEmpty(xshBoo.getXshBos())) {
                            List<PgJgXqVo> pgJgXqVos = xshBoo.getXshBos();
                            for (PgJgXqVo pgJgXqVo : pgJgXqVos) {
                                pgJgXqVo.setPgId(pgId);
                                pgJgXqVo.setDaId(daId);
                                pgJgXqVo.setJyxm(jyxm);
                                pgJgXqVo.setXss(xss);
                                pgJgXqVo.setPgrq(pgrq);
                                pgJgXqVo.setKsjls(ksjls);
                                pgJgXqList.add(pgJgXqVo);
                            }
                        }
                    }
                    saveOrUpdateXshBos(pgJgXqList);  // 批量插入或更新
                }

            } else { // 更新逻辑
                BeanUtil.copyProperties(pgJgVo, po);
                pgId=po.getPgId();
                po.setPgys(pgJgVo.getPgysidName());
                po.initByUpdate();
                updateById(po);

                if (bo.getXshBoos() != null && !bo.getXshBoos().isEmpty()) {
                    List<PgJgXqVo> pgJgXqList = new ArrayList<>();
                    for (XshBoS xshBoo : bo.getXshBoos()) {
                        if (!CollectionUtil.isEmpty(xshBoo.getXshBos())) {
                            List<PgJgXqVo> pgJgXqVos = xshBoo.getXshBos();
                            pgJgXqList.addAll(pgJgXqVos);
                        }
                    }
                    saveOrUpdateXshBos(pgJgXqList);  // 批量插入或更新
                }
            }

            return "操作成功";
        } catch (ParameterException e) {
            log.error("参数异常: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("系统异常: {}", e.getMessage(), e);
            throw new RuntimeException("系统异常，请联系管理员");
        }
    }

    private void saveOrUpdateXshBos(List<PgJgXqVo> pgJgXqVos) {
        for (PgJgXqVo pgJgXqVo : pgJgXqVos) {
            if (pgJgXqVo == null) {
                continue; // 跳过 null 的 PgJgXqVo 对象
            }
            try {
                pgJgXqService.saveOrUpdate(pgJgXqVo);
            } catch (NumberFormatException e) {
                // 处理格式转换错误
                throw new ParameterException("PgJgXqVo 中的 XSS 或 KSJLS 字段格式不正确", e);
            }
        }
    }

    /**
     * 验证对象
     *
     * @param vo 提交参数
     */
    private void validate(PgJgBo vo) {
        if (vo == null) {
            throw new ParameterException("参数不能为空");
        }

    }

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return
     */
    public PageData<PgJgVo> findByPage(PgJgSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    /**
     * 评估一览_多条件模糊查询
     *
     * @return
     */
    public PageData<PgJgDto> getPgJgDataByCriterias(PgJgSearchVo vo) {
        /*hasFilter 是一个布尔值，表示是否存在任何有效的过滤条件，
        如 pgysId、beginDate、endDate、  pgjg）不为 null 或不为空，hasFilter 将为 true，
        执行已经评估的数据查询*/
        /*        boolean hasFilter = (vo.getPgysId() != null && !vo.getPgysId().isEmpty()) ||
                vo.getBeginDate() != null ||
                vo.getEndDate() != null ||
                (vo.getPgjg() != null && !vo.getPgjg().isEmpty());
        boolean hasFilter = (vo.getPgysId() != null && !vo.getPgysId().isEmpty()) ||
                vo.getBeginDate() != null ||
                vo.getEndDate() != null ||
                (vo.getZdlist() != null && !vo.getZdlist().isEmpty()) ||
                (vo.getGlztlist() != null && !vo.getGlztlist().isEmpty()) ||
                (vo.getPgjg() != null && !vo.getPgjg().isEmpty());
                vo.setHasFilter(hasFilter);*/
        //IPage<PgJgDto> ipage = pgJgMapper.findPgJgDataByCriteria1(vo);
        //初始化默认值
        vo.setDefaultValues();
        if ("1".equals(vo.getPgjg())) {
            IPage<PgJgDto> ipage = pgJgMapper.findPgJgDataByCriteria2(vo);
            if (ipage.getRecords() != null) {
                for (PgJgDto record : ipage.getRecords()) {
                    // 根据身份证号设置年龄
                    try {
                        record.setCsrqAndNlFromIdCard();
                    } catch (ParseException e) {
                        throw new ParameterException(e);
                    }

                }
            }
            return PageData.of(ipage);
        } else if ("2".equals(vo.getPgjg())) {
            IPage<PgJgDto> ipage = pgJgMapper.findPgJgDataByCriteria3(vo);
            if (ipage.getRecords() != null) {
                for (PgJgDto record : ipage.getRecords()) {
                    // 根据身份证号设置年龄
                    try {
                        record.setCsrqAndNlFromIdCard();
                    } catch (ParseException e) {
                        throw new ParameterException(e);
                    }

                }
            }
            return PageData.of(ipage);

        }
        return null;
    }

    public PageData<PgJgDto> getPgJgDataByCriteriass(PgJgSearchVo vo) {
        /*hasFilter 是一个布尔值，表示是否存在任何有效的过滤条件，
        如 pgysId、beginDate、endDate、  pgjg）不为 null 或不为空，hasFilter 将为 true，
        执行已经评估的数据查询*/
/*        boolean hasFilter = (vo.getPgysId() != null && !vo.getPgysId().isEmpty()) ||
                vo.getBeginDate() != null ||
                vo.getEndDate() != null ||
                (vo.getPgjg() != null && !vo.getPgjg().isEmpty());
        boolean hasFilter = (vo.getPgysId() != null && !vo.getPgysId().isEmpty()) ||
                vo.getBeginDate() != null ||
                vo.getEndDate() != null ||
                (vo.getZdlist() != null && !vo.getZdlist().isEmpty()) ||
                (vo.getGlztlist() != null && !vo.getGlztlist().isEmpty()) ||
                (vo.getPgjg() != null && !vo.getPgjg().isEmpty());
                vo.setHasFilter(hasFilter);*/
        IPage<PgJgDto> ipage = pgJgMapper.findPgJgDataByCriterias(vo);
//        IPage<PgJgDto> ipage = pgJgMapper.findPgJgDataByCriteria2(vo);
//        List<String> userIds = new ArrayList<>();
        if (ipage.getRecords() != null) {
            for (PgJgDto record : ipage.getRecords()) {
                // 根据身份证号设置年龄
                try {
                    record.setCsrqAndNlFromIdCard();
                } catch (ParseException e) {
                    throw new ParameterException(e);
                }

            }
        }

        return PageData.of(ipage);
    }


    private String getDictName(String type, String code) {
        if (code == null) {
            return null;
        }
        Dict dict = dictService.findByTypeAndCode(type, code);
        return dict != null ? dict.getName() : null;
    }


    /**
     * 通过档案ID和评估日期查询评估_结果VO
     */
    public PgJgDataVo getPgjgDataByDaIdandPgrq(PgSearchVo vo) {
        String daId = vo.getDaId();
        PgJgDataVo pgJgDataVo = new PgJgDataVo();
        if (daId == null) {
            throw new ParameterException("档案ID不能为空");
        }
        PgJgVo pgJgVo = pgJgMapper.findPgJgDataByDaIdandPgrq(vo);
        if (pgJgVo == null) {
            throw new ParameterException("当前日期没有评估记录");
        }
        /* List<PgJgXqVo> pgJgXqVos = new ArrayList<>();
        if (pgJgVo != null && pgJgVo.getJyxms() != null) {
            //JYXM="25OHD,N-MID";
            String jyxms = pgJgVo.getJyxms();
            String pgId = pgJgVo.getPgId();
            if (jyxms != null && !jyxms.isEmpty()) {
                String[] jyxmss = jyxms.split(",");
                for (String s : jyxmss) {
                    PgJgXqVo pgJgXqVo = new PgJgXqVo();
                    LambdaQueryWrapper<PgJgXq> pgJgXqLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    pgJgXqLambdaQueryWrapper.eq(PgJgXq::getJyxm, s)
                            .eq(PgJgXq::getDaId, daId)
                            .eq(PgJgXq::getPgId, pgId)
                            .eq(PgJgXq::getDelFlag, "0");
                    PgJgXq pgJgXq = pgJgXqMapper.selectOne(pgJgXqLambdaQueryWrapper);
                    //BeanUtils.copyProperties(pgJgXq,pgJgXqVo);
                    pgJgXqVo.setPgId(pgJgXq.getPgId());
                    pgJgXqVo.setXqId(pgJgXq.getXqId());
                    pgJgXqVo.setJyxm(pgJgXq.getJyxm());
                    pgJgXqVo.setXss(pgJgXq.getXss());
                    pgJgXqVo.setKsjls(pgJgXq.getKsjls());
                    pgJgXqVo.setDaId(pgJgXq.getDaId());
                    pgJgXqVos.add(pgJgXqVo);
                }
            }
        } */
        pgJgDataVo.setPgJgVo(pgJgVo);
        //pgJgDataVo.setPgJgXqVo(pgJgXqVos);
        return pgJgDataVo;
    }
    /**
     * 评估结果-详情血生化检查查询
     */
    public XshxqVo getPgXshXqDataByPgId(String pgId) {
        XshxqVo xshxqVo = new XshxqVo();
        List<XshBoS> xshBoSs = new ArrayList<>();
        if (StrUtil.isEmpty(pgId)) {
            throw new ParameterException("评估ID不能为空");
        }

        // 查询评估结果
        LambdaQueryWrapper<PgJg> pgJgLambdaQueryWrapper = new LambdaQueryWrapper<>();
        pgJgLambdaQueryWrapper.eq(PgJg::getPgId, pgId)
                .eq(PgJg::getDelFlag, "0");
        PgJg pgJg = pgJgMapper.selectOne(pgJgLambdaQueryWrapper);
        if (pgJg != null) {
            xshxqVo.setPgId(pgJg.getPgId())
                    .setBeginDate(pgJg.getXshjcks())
                    .setEndDate(pgJg.getXshjcjs())
                    .setDaId(pgJg.getDaId());
        } else {
            // 如果没有找到评估结果，可以记录日志或抛出异常
            throw new ParameterException("未找到评估结果，评估ID: " + pgId);
        }

        // 查询详细数据
        LambdaQueryWrapper<PgJgXq> pgJgXqLambdaQueryWrapper = new LambdaQueryWrapper<>();
        pgJgXqLambdaQueryWrapper.eq(PgJgXq::getPgId, pgId)
                .eq(PgJgXq::getDelFlag, "0");
        List<PgJgXq> pgJgXqs = pgJgXqMapper.selectList(pgJgXqLambdaQueryWrapper);

        // 填充 xshBoSs 列表
        for (PgJgXq pgJgXq : pgJgXqs) {
            XshBoS xshBoS = new XshBoS();
            PgJgXqVo pgJgXqVo = new PgJgXqVo();
            BeanUtils.copyProperties(pgJgXq, pgJgXqVo);

            // 填充 xshBoS
            xshBoS.setJyxm(pgJgXq.getJyxm());
            xshBoS.setXss(pgJgXq.getXss());
            xshBoS.setKsjls(pgJgXq.getKsjls());
            xshBoS.setPgId(pgJgXq.getPgId());
            LambdaQueryWrapper<PgJgXq> LambdaQueryWrapper = new LambdaQueryWrapper<>();
            LambdaQueryWrapper.eq(PgJgXq::getJyxm, pgJgXq.getJyxm())
                    .eq(PgJgXq::getDaId, pgJgXq.getDaId())
                    .eq(PgJgXq::getPgId, pgJgXq.getPgId())
                    .eq(PgJgXq::getDelFlag, "0");
            List<PgJgXq> pgJgXqss = pgJgXqMapper.selectList(LambdaQueryWrapper);
            List<PgJgXqVo> pgJgXqVoList = new ArrayList<>();
            for (PgJgXq item : pgJgXqss) {
                PgJgXqVo pgJgXqVo1 = new PgJgXqVo();
                BeanUtils.copyProperties(item, pgJgXqVo1); // 复制单个 pgJgXq 的属性到 pgJgXqVo
                pgJgXqVoList.add(pgJgXqVo1); // 添加到列表
            }

            xshBoS.setXshBos(pgJgXqVoList);
            xshBoS.setTotal((long) pgJgXqss.size());
            xshBoS.setCurrPage((pgJgXq.getKsjls() - 1) / pgJgXq.getXss() + 1); // 计算当前页码
            xshBoS.setPageSize(pgJgXq.getXss()); // 每页显示数
            xshBoS.setTotalPages(((long) pgJgXqss.size() + pgJgXq.getXss() - 1) / pgJgXq.getXss()); // 计算总页数
            xshBoSs.add(xshBoS);
        }

        // 将 xshBoSs 添加到 xshxqVo
        xshxqVo.setXshBoos(xshBoSs);
        return xshxqVo;
    }


}

