package com.shdata.health.gzss.common.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.common.entity.PgJgXq;
import com.shdata.health.gzss.common.mapper.PgJgXqMapper;
import com.shdata.health.gzss.common.vo.PgJgXqSearchVo;
import com.shdata.health.gzss.common.vo.PgJgXqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 评估_结果_详情  Service服务
 *
 * @author 丁文韬
 * @date 2024-07-25
 */
@Service
@Transactional(readOnly = true)
public class PgJgXqService extends BaseService<PgJgXqMapper,PgJgXq> {

    @Autowired
    private PgJgXqMapper pgJgXqMapper;

    /**
     * 保存或更新
     *
     * @param vo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(PgJgXqVo vo) {
        validate(vo);
        PgJgXq po = null;
        if (StrUtil.isNotBlank(vo.getXqId())) {
            po = getById(vo.getXqId());
        }
        if (po == null) { //新增
            LambdaQueryWrapper<PgJgXq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(PgJgXq::getPgId, vo.getPgId())
                    .eq(PgJgXq::getDaId, vo.getDaId())
                    .eq(PgJgXq::getPgId, vo.getPgId())
                    .eq(PgJgXq::getJyxm, vo.getJyxm())
                    .eq(PgJgXq::getPgrq, vo.getPgrq())
                    .eq(PgJgXq::getJyrq, vo.getJyrq())
                    .eq(PgJgXq::getJysz, vo.getJysz())
                    .eq(PgJgXq::getKsjls, vo.getKsjls())
                    .eq(PgJgXq::getXss, vo.getXss())
                    .eq(PgJgXq::getDelFlag,"0");
            PgJgXq pgJgXq = pgJgXqMapper.selectOne(lambdaQueryWrapper);
            if (pgJgXq != null) {
                throw new ParameterException("该评估_结果_详情已存在,请勿重复添加");
            }
            po = BeanUtil.toBean(vo, PgJgXq.class);
            po.setXqId(IdUtil.objectId());
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
    private void validate(PgJgXqVo vo) {
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
    public PageData<PgJgXqVo> findByPage(PgJgXqSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }


}
