package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.shdata.health.gzss.sys.entity.DaJbqkll;
import com.shdata.health.gzss.sys.mapper.DaJbqkllMapper;
import com.shdata.health.gzss.sys.vo.DaJbqkllSearchVo;
import com.shdata.health.gzss.sys.vo.resp.DaJbqkllVo;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.exception.ParameterException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 档案_基本情况履历表  Service服务
 *
 * @author dwt
 * @date 2024-07-12
 */
@Service
@Transactional
public class DaJbqkllService extends BaseService<DaJbqkllMapper,DaJbqkll> {

    /**
     * 保存或更新
     *
     * @param vo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(DaJbqkllVo vo) {
        validate(vo);
        DaJbqkll po = null;
        if (StrUtil.isNotBlank(vo.getLlid())) {
            po = getById(vo.getLlid());
        }
        if (po == null) { //新增
            po = BeanUtil.toBean(vo, DaJbqkll.class);
            po.setLlid(IdUtil.objectId());
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
    private void validate(DaJbqkllVo vo) {
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
    public PageData<DaJbqkllVo> findByPage(DaJbqkllSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }


}
