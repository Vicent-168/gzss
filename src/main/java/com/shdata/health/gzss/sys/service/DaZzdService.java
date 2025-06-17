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
import com.shdata.health.gzss.common.utils.UserUtils;
import com.shdata.health.gzss.sys.entity.DaZzd;
import com.shdata.health.gzss.sys.mapper.DaZzdMapper;
import com.shdata.health.gzss.sys.vo.DaZzdSearchVo;
import com.shdata.health.gzss.sys.vo.DaZzdVo;
import com.shdata.health.gzss.sys.vo.ZzdSearchVo;
import com.shdata.health.gzss.sys.vo.bo.DaZzdBo;
import com.shdata.health.gzss.sys.vo.resp.DaZzdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;


/**
 * 档案_转诊单  Service服务
 *
 * @author 丁文韬
 * @date 2024-07-26
 */
@Service
@Transactional(readOnly = true)
public class DaZzdService extends BaseService<DaZzdMapper, DaZzd> {

    @Autowired
    private DaZzdMapper daZzdMapper;
    @Autowired
    private DictService dictService;

    /**
     * 保存或更新
     *
     * @param vo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(DaZzdVo vo) {
        String code = UserUtils.getOrgan().getCode();
        vo.setZcyljg(code);
        validate(vo);
        DaZzd po = null;
        if (StrUtil.isNotBlank(vo.getId())) {
            po = getById(vo.getId());
        }
        if (po == null) { //新增
            LambdaQueryWrapper<DaZzd> LambdaQueryWrapper = new LambdaQueryWrapper<>();
            LambdaQueryWrapper.eq(DaZzd::getDaId, vo.getDaId())
                    .eq(DaZzd::getZzlb, vo.getZzlb())
                    .eq(DaZzd::getZzzt, vo.getZzzt())
                    .eq(DaZzd::getZryljg, vo.getZryljg())
                    .eq(DaZzd::getZcyljg, vo.getZcyljg())
                    .eq(DaZzd::getZzrq, vo.getZzrq())
                    .eq(DaZzd::getZzysid, vo.getZzysid())
                    .eq(DaZzd::getZcyy, vo.getZcyy());
            DaZzd daZzd = daZzdMapper.selectOne(LambdaQueryWrapper);
            if (daZzd != null) {
                throw new ParameterException("该档案已存在该转诊记录，请勿重复添加");
            }
            po = BeanUtil.toBean(vo, DaZzd.class);
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
    private void validate(DaZzdVo vo) {
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
    public PageData<DaZzdVo> findByPage(DaZzdSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    @Transactional
    public String creatZzd(DaZzdBo bo) {
        DaZzd daZzd = new DaZzd();
        daZzd.setId(IdUtil.objectId())
                .setDaId(bo.getDaId())
                .setZcyy(bo.getZcyy())
                .setZzlb(bo.getZzlb())
                .setZzrq(bo.getZzrq())
                .setZcyljg(bo.getZcyljg())
                .setZryljg(bo.getZryljg())
                .setZzysid(bo.getZzysid())
                .setZcyy(bo.getZcyy())
                .setZzzt(bo.getZzzt());

        try {
            int i = daZzdMapper.insertDaZzd(daZzd);
            return "sucess";
        } catch (Exception e) {
            // 回滚事务
            throw new RuntimeException("插入转诊单失败: " + e.getMessage(), e);
        }


    }

    /**
     * 转诊一览-多条件模糊查询
     */
    public PageData<DaZzdDto> getZzdDataByCriterias(DaZzdSearchVo vo) {
        if (vo.getGlyljg() == null||vo.getGlyljg()=="") {
            vo.setGlyljg(UserUtils.getOrgan().getCode());
        }
        IPage<DaZzdDto> ipage = this.baseMapper.findZzdDataByCriterias(vo);
        if (ipage.getRecords() != null && !ipage.getRecords().isEmpty()) {
            //List<String> codes = new ArrayList<>();
            for (DaZzdDto record : ipage.getRecords()) {
                //codes.add(record.getZryljg());
                try {
                    record.setCsrqAndNlFromIdCard();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                /*record.setXb(getDictName("XB", record.getXb()));
                record.setZzlb(getDictName("ZZLB", record.getZzlb()));
                record.setZzzt(getDictName("ZZZT", record.getZzzt()));*/
            }
            /*Map<String, String> zcyyMap= DataUtil.getOrganNameForMap(codes);
            for (DaZzdDto record : ipage.getRecords()) {
                record.setZryljg(zcyyMap.get(record.getZryljg()));
            }*/
        }
        return PageData.of(ipage);
    }

    //    public PageData<DaZzdDto> getZzdDataByCriterias(DaZzdSearchVo vo) {
//        PageData<DaZzdDto> pageData = new PageData<>();
//        pageData.setTotal(0);
//        pageData.setData(new ArrayList<>());
//        pageData.setCurrPage(vo.getCurrPage());
//        pageData.setPageSize(vo.getPageSize());
//        pageData.setPages(0);
//        // 计算分页参数
//        long offset = (vo.getCurrPage() - 1) * vo.getPageSize();
//        long limit = vo.getPageSize();
//        List<DaZzdDto> daZzdDtos=daZzdMapper.findZzdDataByCriterias(
//                vo.getKeyword(),
//                vo.getBeginDate(),
//                vo.getEndDate(),vo.getGlyljg(),
//                vo.getZzztlist(),
//                vo.getGlgtlist(),vo.getZzlblist(),
//                vo.getZcyy(),vo.getZzysid(), offset, limit);
//        if (daZzdDtos==null&&daZzdDtos.isEmpty()){
//            return pageData;
//        }
//        for (DaZzdDto daZzdDto : daZzdDtos) {
//            try {
//                daZzdDto.setCsrqAndNlFromIdCard();
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            daZzdDto.setXb(getDictName("XB",daZzdDto.getXb()));
//            daZzdDto.setZzlb(getDictName("ZZLB",daZzdDto.getZzlb()));
//            daZzdDto.setZzzt(getDictName("ZZZT",daZzdDto.getZzzt()));
//        }
//        pageData.setTotal(daZzdDtos.size());
//        pageData.setData(daZzdDtos);
//        pageData.setCurrPage(vo.getCurrPage());
//        pageData.setPageSize(vo.getPageSize());
//        pageData.setPages((int) Math.ceil((double) daZzdDtos.size() / vo.getPageSize()));
//        return pageData;
//    }
    private String getDictName(String type, String code) {
        if (code == null) {
            return null;
        }
        Dict dict = dictService.findByTypeAndCode(type, code);
        return dict != null ? dict.getName() : null;
    }

    /**
     * 通过档案ID和转诊日期查询转诊信息
     */
    public DaZzdVo getZzdByDaIdandZzrq(ZzdSearchVo vo) {
        DaZzdVo daZzdVo = daZzdMapper.findZzdByDaIdandZzrq(vo);
        return daZzdVo;
    }
}
