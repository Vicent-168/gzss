package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.common.utils.StringUtils;
import com.shdata.health.gzss.common.dict.DictService;
import com.shdata.health.gzss.sys.entity.BsYjqk;
import com.shdata.health.gzss.sys.mapper.BsYjqkMapper;
import com.shdata.health.gzss.sys.vo.BsYjqkSearchVo;
import com.shdata.health.gzss.sys.vo.BsYjqkVo;
import com.shdata.health.gzss.sys.vo.bo.BsYjqkBo;
import org.springframework.beans.BeanUtils;
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
 * 病史_饮酒情况  Service服务
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Service
@Transactional(readOnly = true)
public class BsYjqkService extends BaseService<BsYjqkMapper, BsYjqk> {

    @Autowired
    private BsYjqkMapper bsYjqkMapper;

    @Autowired
    private DictService dictService;

    /**
     * 保存或更新
     *
     * @param bo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(BsYjqkBo bo) {
        String daId = bo.getDaId();
        List<BsYjqkVo> vos = bo.getBsYjqkVoList();
        List<BsYjqk> bsYjqbs = this.baseMapper.selectList(new LambdaQueryWrapper<BsYjqk>().eq(BsYjqk::getDaId, daId).eq(BsYjqk::getDelFlag, "0"));
        Map<String, BsYjqk> bsYjqkmap = new HashMap<>();
        if (bsYjqbs != null && !bsYjqbs.isEmpty()) {
            bsYjqkmap = bsYjqbs.stream().collect(Collectors.toMap(BsYjqk::getId, Function.identity()));
        }
        if (vos == null || vos.isEmpty()) {
            //删除所有得记录
            new ArrayList<>(bsYjqkmap.keySet()).forEach(this.baseMapper::deleteById);
            return "保存成功";
        }
        for (BsYjqkVo vo : vos) {
            validate(vo);
            String id = vo.getId();
            if (StrUtil.isBlank(id) || !bsYjqkmap.containsKey(id)) {
               /*  List<BsYjqk> bsYjqks = this.baseMapper.selectList(new LambdaQueryWrapper<BsYjqk>()
                        .eq(StrUtil.isNotBlank("daId"), BsYjqk::getDaId, daId)
                        .eq(StrUtil.isNotBlank(vo.getYjzl()), BsYjqk::getYjzl, vo.getYjzl())
                        .eq(vo.getYjmcl() != null, BsYjqk::getYjmcl, vo.getYjmcl())
                        .eq(vo.getYjpl() != null, BsYjqk::getYjpl, vo.getYjpl())
                        .eq(BsYjqk::getDelFlag, "0"));
                if (bsYjqks != null && !bsYjqks.isEmpty()) {
                    throw new ParameterException("该档案已存在该饮酒情况记录");
                } */
                //执行新增操作
                BsYjqk po = BeanUtil.toBean(vo, BsYjqk.class);
                po.setId(IdUtil.objectId());
                po.setDaId(daId);
                po.init();
                save(po);
            } else {
                //执行修改操作
                BsYjqk existingPo = bsYjqkmap.get(id);
                if (existingPo == null) {
                    throw new ParameterException("该档案已存在该饮酒情况记录");
                }
                BeanUtil.copyProperties(vo, existingPo);
                existingPo.initByUpdate();
                updateById(existingPo);
                //从map中移除已经处理的记录
                bsYjqkmap.remove(id);
            }
        }
        //处理删除操作，剩余在map中的记录为需要删除得记录
        if (!bsYjqbs.isEmpty()) {
            new ArrayList<>(bsYjqkmap.keySet()).forEach(this.baseMapper::deleteById);
        }
        return "保存成功";
    }

    /**
     * 验证对象
     *
     * @param vo 提交参数
     */
    private void validate(BsYjqkVo vo) {
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
    public PageData<BsYjqkVo> findByPage(BsYjqkSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    /**
     * 通过档案ID查询病史_饮酒情况
     */
    public List<BsYjqkVo> getYjqkDataByDaId(String daId) {
        List<BsYjqkVo> results = new ArrayList<>();
        if (StringUtils.isEmpty(daId)) {
            throw new IllegalArgumentException("档案号不能为空");
        }
        List<BsYjqk> bsYjqks = bsYjqkMapper.findYjqkDataByDaId(daId);
        if (bsYjqks == null || bsYjqks.isEmpty()) {
            return results;
        }
        for (BsYjqk bsYjqk : bsYjqks) {
            BsYjqkVo bsYjqkvo = new BsYjqkVo();
            BeanUtils.copyProperties(bsYjqk, bsYjqkvo);
            results.add(bsYjqkvo);
        }
        return results;
    }

    private String getDictName(String type, String code) {
        if (code == null) {
            return null;
        }
        Dict dict = dictService.findByTypeAndCode(type, code);
        return dict != null ? dict.getName() : null;
    }
}
