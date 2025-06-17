package com.shdata.health.gzss.common.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.common.entity.JyxmSet;
import com.shdata.health.gzss.common.mapper.JyxmSetMapper;
import com.shdata.health.gzss.common.vo.JyxmSetSearchVo;
import com.shdata.health.gzss.common.vo.JyxmSetVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 检验指标配置表  Service服务
 *
 * @author dwt
 * @date 2024-07-18
 */
@Service
@Transactional(readOnly = true)
public class JyxmSetService extends BaseService<JyxmSetMapper,JyxmSet> {

    @Autowired
    private JyxmSetMapper jyxmSetMapper;
    /**
     * 根据检验项目名称获取检验指标集合
     *
     */

    public List<JyxmSetVo> getJyzlByJyxm(String jyxmmc){
        List<JyxmSetVo> jyxmSetVos = jyxmSetMapper.findJyzlByJyxm(jyxmmc);
        return jyxmSetVos; // 如果查询结果为空，返回null
    }

    /**
     * 保存或更新
     *
     * @param vo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(JyxmSetVo vo) {
        validate(vo);
        JyxmSet po = null;
        if (StrUtil.isNotBlank(vo.getId())) {
            po = getById(vo.getId());
        }
        if (po == null) { //新增
            po = BeanUtil.toBean(vo, JyxmSet.class);
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
    private void validate(JyxmSetVo vo) {
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
    public PageData<JyxmSetVo> findByPage(JyxmSetSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }


    /**
     * 根据检验项目代码、性别类型和年龄查询检验指标配置表
     *
     * @param jyXmdm 检验项目代码
     * @param sexType 性别类型
     * @param age 年龄
     * @return 符合条件的检验指标配置表列表
     */
    public JyxmSetVo findByCriteria(String jyXmdm, String sexType, Integer age) {
        List<JyxmSetVo> jyxmSetVos = jyxmSetMapper.findByCriteria(jyXmdm);
        for (JyxmSetVo jyxmSetVo : jyxmSetVos) {
            //性别不为空并且性别不相同的数据过滤掉
            if (StringUtils.isNotBlank(jyxmSetVo.getSexType()) && !StringUtils.equals(jyxmSetVo.getSexType(), sexType)) {
                continue;
            }
            //开始年龄不为空且入参年龄小于标准的开始年龄数据不符合要求就过滤掉
            if (jyxmSetVo.getAgeFrom() != null && jyxmSetVo.getAgeFrom().compareTo(age) > 0) {
                continue;
            }
            //截至年龄不为空并且入参年龄大于标准的截至年龄数据不符合要求就过滤掉数据
            if (jyxmSetVo.getAgeTo() != null && jyxmSetVo.getAgeTo().compareTo(age) < 0) {
                continue;
            }
            return jyxmSetVo;
        }
        //所有入参走完了都没有找到符合的数据就返回为空
        return null;
    }
    /**
     * 根据检验种类获取检验项目
     */
    public List<JyxmSetVo> getJyxmByJyzl(String bigCode) {
        List<JyxmSetVo> jyxmSetVos=jyxmSetMapper.findJyxmByJyzl(bigCode);
        return jyxmSetVos;
    }
}
