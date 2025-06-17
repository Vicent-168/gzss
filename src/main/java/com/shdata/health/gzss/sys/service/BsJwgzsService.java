package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.sys.entity.BsJwgzs;
import com.shdata.health.gzss.sys.mapper.BsJwgzsMapper;
import com.shdata.health.gzss.sys.vo.BsJwgzsSearchVo;
import com.shdata.health.gzss.sys.vo.BsJwgzsVo;
import com.shdata.health.gzss.sys.vo.JwgzsSearchVo;
import com.shdata.health.gzss.sys.vo.bo.BsJwgzsBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 病史既往骨折史  Service服务
 *
 * @author dwt
 * @date 2024-07-19
 */
@Service
@Transactional(readOnly = true)
public class BsJwgzsService extends BaseService<BsJwgzsMapper, BsJwgzs> {

    @Autowired
    private BsJwgzsMapper bsJwgzsMapper;

    /**
     * 既往骨折史保存或更新
     *
     * @param bo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(BsJwgzsBo bo) {
        String daId = bo.getDaId();
        // 获取当前数据库中与该档案ID相关的记录
        List<BsJwgzs> jwgzsList = this.baseMapper.selectList(new LambdaQueryWrapper<BsJwgzs>().eq(BsJwgzs::getDaId, daId).eq(BsJwgzs::getDelFlag, "0"));
        Map<String, BsJwgzs> jwgzMap = new HashMap<>();
        if (jwgzsList != null || !jwgzsList.isEmpty()) {
            jwgzMap = jwgzsList.stream().collect(Collectors.toMap(BsJwgzs::getId, Function.identity()));
        }
        List<BsJwgzsVo> vos = bo.getBsJwgzsVoList();
        if (vos == null || vos.isEmpty()) {
            //删除所有得记录
            new ArrayList<>(jwgzMap.keySet()).forEach(this.baseMapper::deleteById);
            return "保存成功";
        }
        for (BsJwgzsVo vo : vos) {
            validate(vo);
            String id = vo.getId();
            if (id == null || !jwgzMap.containsKey(id)) {
               /*  //新增去重
                List<BsJwgzs> jwgzs = this.baseMapper.selectList(new LambdaQueryWrapper<BsJwgzs>()
                        .eq(StrUtil.isNotBlank("daId"), BsJwgzs::getDaId, daId)
                        .eq(StrUtil.isNotBlank(vo.getBw()), BsJwgzs::getBw, vo.getBw())
                        .eq(vo.getSj() != null, BsJwgzs::getSj, vo.getSj())
                        .eq(StrUtil.isNotBlank(vo.getYy()), BsJwgzs::getYy, vo.getYy())
                        .eq(StrUtil.isNotBlank(vo.getJbwzId()), BsJwgzs::getJbwzId, vo.getJbwzId())
                        .eq(BsJwgzs::getDelFlag, "0"));
                if (jwgzs != null && !jwgzs.isEmpty()) {
                    throw new ParameterException("该档案已存在该既往骨折史记录");
                } */
                //执行新增操作
                BsJwgzs po = BeanUtil.toBean(vo, BsJwgzs.class);
                po.setId(IdUtil.objectId());
                po.setDaId(daId);
                po.init();
                save(po);
            } else {
                //执行修改操作
                BsJwgzs existingPo = jwgzMap.get(id);
                if (existingPo == null) {
                    throw new ParameterException("该既往骨折史记录不存在");
                }
                BeanUtil.copyProperties(vo, existingPo);
                existingPo.initByUpdate();
                updateById(existingPo);
                //从map中移除已经处理的记录
                jwgzMap.remove(id);
            }
        }
        //处理删除操作，剩余在map中的记录为需要删除得记录
        if (!jwgzsList.isEmpty()) {
            new ArrayList<>(jwgzMap.keySet()).forEach(this.baseMapper::deleteById);
        }
        return "保存成功";
    }

    /**
     * 验证对象
     *
     * @param vo 提交参数
     */
    private void validate(BsJwgzsVo vo) {
        if (vo == null) {
            throw new ParameterException("参数不能为空");
        }


       /*  if (StrUtil.isBlank(vo.getDaId())) {
            throw new ParameterException("档案ID不能为空");
        }

        if (vo.getSj() == null) {
            throw new ParameterException("时间不能为空");
        }

        if (StrUtil.isBlank(vo.getBw())) {
            throw new ParameterException("部位不能为空");
        } */

    }

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return
     */
    public PageData<BsJwgzsVo> findByPage(BsJwgzsSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    /**
     * 通用子页面
     * 通过档案ID查询病史既往骨折史VO
     */
    public List<BsJwgzsVo> getBsJwgzsDataByDaId(String daId) {
        List<BsJwgzsVo> bsJwgzsVos = bsJwgzsMapper.selectByDaIdWithPagination(daId);
        return bsJwgzsVos;
    }

    /**
     * 通过档案ID和疾病问诊id查询病史既往骨折史
     */
    public List<BsJwgzsVo> getBsJwgzsDataByDaIdandZdrq(JwgzsSearchVo vo) {
        if (vo.getDaId() == null) {
            throw new ParameterException("档案ID不能为空");
        }
        if (vo.getJbwzId() == null) {
            throw new ParameterException("疾病问诊主键不能为空");
        }
        List<BsJwgzsVo> bsJwgzsVos = bsJwgzsMapper.selectByDaIdandZdrq(vo);
        return bsJwgzsVos;
    }
}
