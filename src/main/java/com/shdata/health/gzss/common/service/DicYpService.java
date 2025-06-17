package com.shdata.health.gzss.common.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.common.dict.DictService;
import com.shdata.health.gzss.common.entity.DicYp;
import com.shdata.health.gzss.common.mapper.DicYpMapper;
import com.shdata.health.gzss.common.vo.DicYpSearchVo;
import com.shdata.health.gzss.common.vo.DicYpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 药品目录  Service服务
 *
 * @author dwt
 * @date 2024-07-19
 */
@Service
@Transactional(readOnly = true)
public class DicYpService extends BaseService<DicYpMapper,DicYp> {

    @Autowired
    private DictService dictService;

    @Autowired
    private DicYpMapper dicYpMapper;

    /**
     * 保存或更新
     *
     * @param vo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(DicYpVo vo) {
        validate(vo);
        DicYp po = null;
        if (StrUtil.isNotBlank(vo.getId())) {
            po = getById(vo.getId());
        }
        if (po == null) { //新增
            LambdaQueryWrapper<DicYp> LambdaQueryWrapper = new LambdaQueryWrapper<>();
            LambdaQueryWrapper.eq(StrUtil.isNotBlank(vo.getGg()),DicYp::getGg, vo.getGg())
                    .eq(StrUtil.isNotBlank(vo.getDw()),DicYp::getDw, vo.getDw())
                    .eq(StrUtil.isNotBlank(vo.getJx()),DicYp::getJx, vo.getJx())
                    .eq(StrUtil.isNotBlank(vo.getDmlx()),DicYp::getDmlx, vo.getDmlx())
                    .eq(StrUtil.isNotBlank(vo.getDflCd()),DicYp::getDflCd, vo.getDflCd())
                    .eq(StrUtil.isNotBlank(vo.getYwsytj()),DicYp::getYwsytj, vo.getYwsytj())
                    .eq(StrUtil.isNotBlank(vo.getYmHxm()),DicYp::getYmHxm, vo.getYmHxm())
                    .eq(StrUtil.isNotBlank(vo.getPinyin()),DicYp::getPinyin, vo.getPinyin())
                    .eq(StrUtil.isNotBlank(vo.getYnYljgdm()),DicYp::getYnYljgdm, vo.getYnYljgdm())
                    .eq(StrUtil.isNotBlank(vo.getYwSpm()),DicYp::getYwSpm, vo.getYwSpm())
                    .eq( DicYp::getDelFlag, "0");
            DicYp dicYp = dicYpMapper.selectOne(LambdaQueryWrapper);
            if (dicYp != null) {
                throw new ParameterException("该药品已存在,请勿重复添加");
            }
            po = BeanUtil.toBean(vo, DicYp.class);
            po.setId(IdUtil.objectId());
            po.setYwdm(IdUtil.objectId());
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
    private void validate(DicYpVo vo) {
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
    public List<DicYpVo> findByPage(DicYpSearchVo search) {
        List<DicYpVo> results  = baseMapper.findList(search);
//        for (DicYpVo dicYpVo : resultPage.getRecords()) {
//            // 根据数据字典将药品分类code转换成中文名
//            if (dicYpVo.getDflCd() != null) {
//                Dict gbywflDict = dictService.findByTypeAndCode("GBYWFL", dicYpVo.getDflCd());
//                if (gbywflDict != null) {
//                    dicYpVo.setDflCd(gbywflDict.getName());
//                }
//            }
//
//        }
        return results;
    }

    /**
     * 通过药物代码查询药品目录
     */
    public List<DicYpVo> getList() {

        return baseMapper.getList();
    }
    /**
     * 根据药物代码查询药品信息
     */
    public DicYpVo getYpByYwdm(String ywdm) {
        if (StrUtil.isBlank(ywdm)) {
            throw new ParameterException("药品代码不能为空");
        }

        return baseMapper.getYpByYwdm(ywdm);
    }
}
