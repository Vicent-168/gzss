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
import com.shdata.health.gzss.sys.entity.SfJbwz;
import com.shdata.health.gzss.sys.mapper.SfJbwzMapper;
import com.shdata.health.gzss.sys.vo.BsJwgzsVo;
import com.shdata.health.gzss.sys.vo.SfJbwzSearchVo;
import com.shdata.health.gzss.sys.vo.SfJbwzVo;
import com.shdata.health.gzss.sys.vo.SfyljbwzVo;
import com.shdata.health.gzss.sys.vo.bo.BsJwgzsBo;
import com.shdata.health.gzss.sys.vo.bo.SfJbwzBo;
import com.shdata.health.gzss.sys.vo.resp.SfJbwzDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * 疾病问诊  Service服务
 *
 * @author dwt
 * @date 2024-07-22
 */
@Service
@Transactional(readOnly = true)
public class SfJbwzService extends BaseService<SfJbwzMapper, SfJbwz> {

    @Autowired
    private SfJbwzMapper sfJbwzMapper;

    @Autowired
    private DictService dictService;

    @Autowired
    private BsJwgzsService bsJwgzsService;

    /**
     * 保存或更新
     *
     * @param bo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(SfJbwzBo bo) {
        String daId = bo.getDaId();
        SfJbwzVo vo = bo.getSfJbwzVo();
        String jbwzId = bo.getSfJbwzVo().getId();
        vo.setDaId(daId);
        List<BsJwgzsVo> bsJwgzsVoList = bo.getBsJwgzsVoList();
        //更新骨密度小表单
        if (bsJwgzsVoList!=null) {
            for (BsJwgzsVo bsJwgzsVo : bsJwgzsVoList) {
                bsJwgzsVo.setDaId(daId);
                bsJwgzsVo.setJbwzId(jbwzId);
            }
        }
        BsJwgzsBo bsJwgzsBo = new BsJwgzsBo();
        bsJwgzsBo.setDaId(daId);
        bsJwgzsBo.setBsJwgzsVoList(bsJwgzsVoList);
        bsJwgzsService.saveOrUpdate(bsJwgzsBo);
        validate(vo);
        SfJbwz po = null;
        if (StrUtil.isNotBlank(vo.getId())) {
            po = getById(vo.getId());
        }
        if (po == null) { //新增
            LambdaQueryWrapper<SfJbwz> LambdaQueryWrapper = new LambdaQueryWrapper<>();
            LambdaQueryWrapper.eq(StrUtil.isNotBlank(daId), SfJbwz::getDaId, daId)
                    .eq(vo.getWzrq() != null, SfJbwz::getWzrq, vo.getWzrq())
                    .eq(StrUtil.isNotBlank(vo.getZz()), SfJbwz::getZz, vo.getZz())
                    .eq(StrUtil.isNotBlank(vo.getXfgz()), SfJbwz::getXfgz, vo.getXfgz())
                    .eq(StrUtil.isNotBlank(vo.getXfzz()), SfJbwz::getXfzz, vo.getXfzz())
                    .eq(StrUtil.isNotBlank(vo.getXfgzcs()), SfJbwz::getXfgzcs, vo.getXfgzcs())
                    .eq(StrUtil.isNotBlank(vo.getSfqjfsdd()), SfJbwz::getSfqjfsdd, vo.getSfqjfsdd())
                    .eq(StrUtil.isNotBlank(vo.getDdcs()), SfJbwz::getDdcs, vo.getDdcs())
                    .eq(StrUtil.isNotBlank(vo.getZlqkycx()), SfJbwz::getZlqkycx, vo.getZlqkycx())
                    .eq(StrUtil.isNotBlank(vo.getGzsszwxys()), SfJbwz::getGzsszwxys, vo.getGzsszwxys())
                    .eq(StrUtil.isNotBlank(vo.getSgSd()), SfJbwz::getSgSd, vo.getSgSd())
                    .eq(StrUtil.isNotBlank(vo.getSffs()), SfJbwz::getSffs, vo.getSffs())
                    .eq(StrUtil.isNotBlank(vo.getSfysid()), SfJbwz::getSfysid, vo.getSfysid())
                    .eq(StrUtil.isNotBlank(vo.getGljg()), SfJbwz::getGljg, vo.getGljg());
            List<SfJbwz> sfJbwzs = sfJbwzMapper.selectList(LambdaQueryWrapper);
            if (sfJbwzs.size() > 0) {
                throw new ParameterException("该档案已存在该疾病问诊信息，请勿重复添加！");
            }
            po = BeanUtil.toBean(vo, SfJbwz.class);
            po.setId(IdUtil.objectId());
            po.setDaId(daId);
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
    private void validate(SfJbwzVo vo) {
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
    public PageData<SfJbwzVo> findByPage(SfJbwzSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    /**
     * 随访一览-疾病问诊多条件查询
     */
    public PageData<SfJbwzDto> getJbwzDataByCriteria(SfJbwzSearchVo vo) {


        IPage<SfJbwzDto> iPage = sfJbwzMapper.findJbwzDataByCriteria(vo);
        if (iPage.getRecords() != null || !iPage.getRecords().isEmpty()) {
            for (SfJbwzDto record : iPage.getRecords()) {
                // 根据身份证号设置出生日期和年龄
                try {
                    record.setCsrqAndNlFromIdCard();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                /*record.setZz(getDictName("ZZ", record.getZz()));
                record.setXb(getDictName("XB", record.getXb()));
                record.setSgsd(getDictName("YW", record.getSgsd()));
                record.setXfgz(getDictName("YW", record.getXfgz()));
                record.setSfqjfsdd(getDictName("YW", record.getSfqjfsdd()));*/
            }

        }
        return PageData.of(iPage);
    }


    private String getDictName(String type, String code) {
        if (code == null) {
            return null;
        }
        Dict dict = dictService.findByTypeAndCode(type, code);
        return dict != null ? dict.getName() : null;
    }

    /**
     * 通过档案ID和问诊日期查询疾病问诊VO
     */
    public SfJbwzVo getJbwzDataByDaIdandDate(SfyljbwzVo vo) {
        if (StrUtil.isBlank(vo.getDaId() )) {
            throw new ParameterException("档案id参数不能为空");
        }
        if (vo.getWzrq() == null) {
            throw new ParameterException("问诊日期参数不能为空");
        }
        SfJbwzVo sfJbwzVo = this.baseMapper.findJbwzDataByDaIdandDate(vo);
        return sfJbwzVo;
    }
}
