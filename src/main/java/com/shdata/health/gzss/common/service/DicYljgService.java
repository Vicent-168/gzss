package com.shdata.health.gzss.common.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.shdata.health.gzss.common.entity.DicYljg;
import com.shdata.health.gzss.common.mapper.DicYljgMapper;
import com.shdata.health.gzss.common.vo.DicYljgSearchVo;
import com.shdata.health.gzss.common.vo.DicYljgVo;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.exception.ParameterException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 医疗机构  Service服务
 *
 * @author 丁文韬
 * @date 2024-11-25
 */
@Service
@Transactional(readOnly = true)
public class DicYljgService extends BaseService<DicYljgMapper,DicYljg> {

    /**
     * 保存或更新
     *
     * @param vo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(DicYljgVo vo) {
        validate(vo);
        DicYljg po = null;
        if (StrUtil.isNotBlank(vo.getId())) {
            po = getById(vo.getId());
            if (po == null) {
                throw new ParameterException("无效的ID");
            }
        }
        if (po == null) { //新增
            po = BeanUtil.toBean(vo, DicYljg.class);
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
    private void validate(DicYljgVo vo) {
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
    public PageData<DicYljgVo> findByPage(DicYljgSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    /**
     * 获取医疗机构代码及其名称
     */
    public List<DicYljgVo> getList() {
        return this.baseMapper.findList();
    }
}
