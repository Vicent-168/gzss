package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.common.dict.DictService;
import com.shdata.health.gzss.common.enums.BwCodeEnum;
import com.shdata.health.gzss.common.utils.UserUtils;
import com.shdata.health.gzss.sys.entity.SfGmdjc;
import com.shdata.health.gzss.sys.mapper.PgJgMapper;
import com.shdata.health.gzss.sys.mapper.SfGmdjcMapper;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.resp.PgjgGmdDto;
import com.shdata.health.gzss.sys.vo.resp.PgjgXqGmdDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 随访_骨密度检查  Service服务
 *
 * @author dwt
 * @date 2024-07-16
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class SfGmdjcService extends BaseService<SfGmdjcMapper, SfGmdjc> {

    @Autowired
    private DictService dictService;

    @Autowired
    private SfGmdjcMapper sfGmdjcMapper;
    @Autowired
    private PgJgMapper pgJgMapper;

    @Autowired
    private PgJgService pgJgService;

    /**
     * 保存或更新
     *
     * @param vo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(SfGmdjcVo vo) {
        validate(vo);
        SfGmdjc po = null;
        if (StrUtil.isNotBlank(vo.getId())) {
            po = getById(vo.getId());
        }
        if (po == null) { //新增
            /* LambdaQueryWrapper<SfGmdjc> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(StrUtil.isNotBlank(vo.getDaId()), SfGmdjc::getDaId, vo.getDaId())
                    .eq(StrUtil.isNotBlank(vo.getJcyljg()), SfGmdjc::getJcyljg, vo.getJcyljg())
                    .eq(vo.getTzL1() != null, SfGmdjc::getTzL1, vo.getTzL1())
                    .eq(vo.getTzL2() != null, SfGmdjc::getTzL2, vo.getTzL2())
                    .eq(vo.getTzL3() != null, SfGmdjc::getTzL3, vo.getTzL3())
                    .eq(vo.getTzL4() != null, SfGmdjc::getTzL4, vo.getTzL4())
                    .eq(vo.getTzL1L4() != null, SfGmdjc::getTzL1L4, vo.getTzL1L4())
                    .eq(vo.getTzQh() != null, SfGmdjc::getTzQh, vo.getTzQh())
                    .eq(vo.getTzGgj() != null, SfGmdjc::getTzGgj, vo.getTzGgj())
                    .eq(vo.getDxaJcrq() != null, SfGmdjc::getDxaJcrq, vo.getDxaJcrq());
            SfGmdjc sfGmdjc = sfGmdjcMapper.selectOne(lambdaQueryWrapper);
            if (sfGmdjc != null) {
                throw new ParameterException("该患者已存在该部位数据，请勿重复录入！");
            } */
            po = BeanUtil.toBean(vo, SfGmdjc.class);
            po.setId(IdUtil.objectId());
            po.init();
            save(po);
            return "保存成功";
        } else {
            BeanUtil.copyProperties(vo, po);
            po.initByUpdate();
            updateById(po);
            return "更新成功";
        }
    }

    /**
     * 验证对象
     *
     * @param vo 提交参数
     */
    private void validate(SfGmdjcVo vo) {
        if (vo == null) {
            throw new ParameterException("参数不能为空");
        }

    }

    /**
     * 随访一览_骨密度检查
     *
     * @param search 分页查询对象
     * @return
     */
    public PageData<SfGmdJcYlo> findByPage(SfGmdjcSearchVo search) {
        PageData<SfGmdJcYlo> pageData = new PageData<>();
        pageData.setCurrPage(search.getCurrPage());
        pageData.setPageSize(search.getPageSize());
        pageData.setTotal(0);
        pageData.setData(new ArrayList<SfGmdJcYlo>());
        pageData.setPages(0);
        //做非空判断，传值为null或者为空字符串的时候赋值为默认值
        if (StrUtil.isBlank( search.getJcyljg())) {
            search.setJcyljg(UserUtils.getOrgan().getCode());
        }
        if(StrUtil.isBlank( search.getYfCode())){
            search.setYfCode("2");
        }
        IPage<SfGmdJcYlo> results;
        if (CollectionUtils.isNotEmpty(search.getTzCodes()) && CollectionUtils.isNotEmpty(search.getBwCodes())) {
            List<String> sqlList = new ArrayList<>();
            List<String> tzCodes = search.getTzCodes();
            List<String> bwCodes = search.getBwCodes();
            for (String bwCode : bwCodes) {
                String bwName = BwCodeEnum.getNameByCode(bwCode);
                // -1 <TZ_L1_L4 and TZ_L1_L4> 1
                // 生成每个部位代码对应的SQL条件
                String sql = getsql(bwName, tzCodes);
                sqlList.add(sql);
            }


            if ("1".equals(search.getYfCode())) {
                //与条件下的查询语句
                results = sfGmdjcMapper.searchSfGmdjc1(search, sqlList);
                // 如果查询结果为空，直接返回空分页数据
                if (results.getRecords() == null || results.getRecords().isEmpty()) {
                    return pageData;
                }

            } else {
//                //或条件下的查询语句
//                results = sfGmdjcMapper.searchSfGmdjc2( search,search.getKeyword(),
//                        search.getJcyljg(), search.getBeginDate(),
//                        search.getEndDate(), sqlList);
                //或条件下的查询语句
                results = sfGmdjcMapper.searchSfGmdjc2(search, sqlList);
                // 如果查询结果为空，直接返回空分页数据
                if (results.getRecords() == null || results.getRecords().isEmpty()) {
                    return pageData;
                }
            }
            // 设置出生日期和年龄
            for (SfGmdJcYlo item : results.getRecords()) {
                try {
                    // 根据身份证号设置出生日期和年龄、性别
                    item.setCsrqAndNlFromIdCard();
                } catch (ParseException e) {
                    log.error("设置出生日期和年龄时出错", e);
                }
            }

            // 返回带有分页信息的数据
            return pageData.of(results);
        }
        results = sfGmdjcMapper.searchSfGmdjc3(search);
        for (SfGmdJcYlo item : results.getRecords()) {
            try {
                // 根据身份证号设置出生日期和年龄、性别
                item.setCsrqAndNlFromIdCard();
            } catch (ParseException e) {
                log.error("设置出生日期和年龄时出错", e);
            }
        }

        // 没有匹配的查询条件时，返回空的分页数据
        return pageData.of(results);
    }

    private String getsql(String bwName, List<String> tzCodes) {
        String sql;
        if (tzCodes.size() == 1) {
            if (tzCodes.contains("01")) {
                sql = bwName + " >= -1 AND " + bwName + " <= 1";
            } else if (tzCodes.contains("02")) {
                sql = bwName + " >= -2.5 AND " + bwName + " <= -1";
            } else if (tzCodes.contains("03")) {
                sql = bwName + " < -2.5";
            } else {
                throw new IllegalArgumentException("无效的T值代码: " + tzCodes);
            }
        } else if (tzCodes.size() == 2) {
            if (tzCodes.contains("01") && tzCodes.contains("02")) {
                sql = bwName + " >= -2.5 AND " + bwName + " <= 1";
            } else if (tzCodes.contains("01") && tzCodes.contains("03")) {
                sql = "(" + bwName + " < -2.5 OR (" + bwName + " >= -1 AND " + bwName + " <= 1))";
            } else if (tzCodes.contains("02") && tzCodes.contains("03")) {
                sql = bwName + " < -1";
            } else {
                throw new IllegalArgumentException("无效的T值代码组合: " + tzCodes);
            }
        } else if (tzCodes.size() == 3) {
            if (tzCodes.contains("01") && tzCodes.contains("02") && tzCodes.contains("03")) {
                sql = bwName + " <= 1";
            } else {
                throw new IllegalArgumentException("无效的T值代码组合: " + tzCodes);
            }
        } else {
            throw new IllegalArgumentException("无效的T值代码组合: " + tzCodes);
        }
        return sql;
    }


    // 根据 bwCode 获取对应的字段名
    private String getColumnName(String bwCode) {
        switch (bwCode) {
            case "01":
                return "TZ_L1_L4";
            case "02":
                return "TZ_QH";
            case "03":
                return "TZ_GGJ";
            default:
                throw new IllegalArgumentException("Invalid bwCode: " + bwCode);
        }
    }

    // 根据 tzCode 获取对应的值范围
    private String getRange(String tzCode) {
        switch (tzCode) {
            case "01":
                return "BETWEEN -1 AND 1";
            case "02":
                return "BETWEEN -2.5 AND -1";
            case "03":
                return "< -2.5";
            default:
                throw new IllegalArgumentException("Invalid tzCode: " + tzCode);
        }
    }


    /**
     * 根据daId 和分页参数 查询共同子页面骨密度检查
     *
     * @return List<SfGmdjcVo>
     */
    public PageData<SfGmdjcVo> getListDataByDaid(SfGmdjcSearchVo vo) {
        if (vo.getDaId() == null || vo.getDaId().isEmpty()) {
            throw new IllegalArgumentException("档案ID不能为空");
        }
        // 创建一个空的分页数据对象
        PageData<SfGmdjcVo> pageData = new PageData<>();
        pageData.setTotal(0);
        pageData.setPageSize(vo.getPageSize());
        pageData.setCurrPage(vo.getCurrPage());
        pageData.setData(new ArrayList<SfGmdjcVo>());
        IPage<SfGmdjcVo> page = sfGmdjcMapper.selectByDaIdWithPagination(vo);
        // 如果查询结果为空，直接返回空分页数据
        if (page.getRecords() == null || page.getRecords().isEmpty()) {

            return pageData;
        }
//        List<SfGmdjcVo> sfGmdjcVoList = page.getRecords().stream().map(entity -> {
//            SfGmdjcVo sfGmdjcVo = new SfGmdjcVo();
//            BeanUtils.copyProperties(entity, sfGmdjcVo);
//            // 转换SFFS字段-随访方式
//            //sfGmdjcVo.setSffs(getDictName("SFFS",entity.getSffs()));
//            if (entity.getSffs() != null) {
//                Dict sffsDict = dictService.findByTypeAndCode("SFFS", entity.getSffs());
//               if (sffsDict != null) {
//                    vo.setSffs(sffsDict.getName());
//               }
//            }
//            return sfGmdjcVo;
//        }).collect(Collectors.toList());

        return pageData.of(page);

    }

    private String getDictName(String type, String code) {
        if (code == null) {
            return null;
        }
        Dict dict = dictService.findByTypeAndCode(type, code);
        return dict != null ? dict.getName() : null;
    }

    /**
     * 评估结果-新建页面骨密度检查查询
     */
    public PageData<PgjgGmdDto> getPgjgGmdDataByCriterias(PgjgGmdSearchVo vo) {
        if (vo.getDaId() == null || vo.getDaId().isEmpty()) {
            throw new ParameterException("档案ID不能为空");
        }
        //初始化查询条件
        vo.initDefaults();
        IPage<PgjgGmdDto> results = sfGmdjcMapper.getPgjgGmdDataByCriterias(vo);
        return PageData.of(results);
    }

    /**
     * 评估结果-详情页面骨密度检查查询
     */
    public PageData<PgjgXqGmdDto> getPgjgXqGmdDataByDaId(PgSearchVo pgSearchVo) {
        if (pgSearchVo.getDaId() == null || pgSearchVo.getDaId().isEmpty() ) {
            throw new ParameterException("档案ID不能为空");
        }

        PgJgDataVo pgjgDatavo = pgJgService.getPgjgDataByDaIdandPgrq(pgSearchVo);
        PgJgVo pgJgVo = pgjgDatavo.getPgJgVo();
        Date gmdJcks = null;
        Date gmdJcjs = null;
        Long gmdXss = null;
        Long gmdKsjls = null;
        String pgId = pgJgVo.getPgId();
        if (pgJgVo != null)  {
            gmdJcks = pgJgVo.getGmdjcks();
            gmdJcjs = pgJgVo.getGmdjcjs();
            gmdXss = pgJgVo.getGmdXss();
            gmdKsjls = pgJgVo.getGmdKsjls();
        }
        //构建查询入参
        PgjgXqGmdSearchVo vo = new PgjgXqGmdSearchVo();
        vo.setDaId(pgSearchVo.getDaId());
        vo.setGmdJcks(gmdJcks);
        vo.setGmdJcjs(gmdJcjs);
        vo.setPageSize(gmdXss);
        vo.setCurrPage((gmdKsjls/gmdXss)+1);
        IPage<PgjgXqGmdDto> page = sfGmdjcMapper.findPgjgXqGmdDataByCriterias(vo);
        if (page.getRecords() != null && !page.getRecords().isEmpty()) {
            for (PgjgXqGmdDto record : page.getRecords()) {
                //设置该档案id下的查询时间范围
                record.setGmdJcks(gmdJcks);
                record.setGmdJcjs(gmdJcjs);
                record.setGmdXss(gmdXss);
                record.setGmdKsjls(gmdKsjls);
                record.setPgId(pgId);
            }
        }
        return PageData.of(page);
    }

    /**
     * 通过档案ID查询随访最新骨密度信息
     */
    public SfGmdjcVo fetchLatestGmdCheckData(String daId) {
        if (StrUtil.isBlank(daId)) {
            throw new IllegalArgumentException("档案ID不能为空");
        }
        //通过daId查询最新骨密度信息
        SfGmdjcVo sfGmdjcVo = sfGmdjcMapper.findLatestGmdCheckDataByDaId(daId);
        return sfGmdjcVo;
    }
    /**
     * 通过档案ID和诊断日期查询随访骨密度信息
     */
    public SfGmdjcVo fetchGmdCheckDataBydaIdandZdrq(GmdlySearchvo vo) {
        SfGmdjcVo sfGmdjcVo=this.baseMapper.findGmdCheckDataBydaIdandZdrq(vo);
        return sfGmdjcVo;
    }
}
