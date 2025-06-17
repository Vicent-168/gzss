package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.common.dict.DictService;
import com.shdata.health.gzss.sys.entity.BsMxbFys;
import com.shdata.health.gzss.sys.mapper.BsMxbFysMapper;
import com.shdata.health.gzss.sys.vo.BsMxbFysSearchVo;
import com.shdata.health.gzss.sys.vo.BsMxbFysVo;
import com.shdata.health.gzss.sys.vo.bo.BsMxbFysBo;
import org.apache.commons.lang3.StringUtils;
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
 * 病史_慢性病服药史  Service服务
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Service
@Transactional(readOnly = true)
public class BsMxbFysService extends BaseService<BsMxbFysMapper, BsMxbFys> {

    @Autowired
    private BsMxbFysMapper bsMxbFysMapper;

    @Autowired
    private DictService dictService;

    /**
     * 保存或更新
     *
     * @param bo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(BsMxbFysBo bo) {

        String daId = bo.getDaId();
        List<BsMxbFys> bsMxbFys = this.baseMapper.selectList(new LambdaQueryWrapper<BsMxbFys>().eq(BsMxbFys::getDaId, daId).eq(BsMxbFys::getDelFlag, "0"));
        Map<String, BsMxbFys> bsMxbFysMap = new HashMap<>();
        if (bsMxbFys != null && !bsMxbFys.isEmpty()) {
            bsMxbFysMap = bsMxbFys.stream().collect(Collectors.toMap(BsMxbFys::getId, Function.identity()));
        }
        List<BsMxbFysVo> vos = bo.getBsMxbFysVoList();
        if (vos == null || vos.isEmpty()) {
            //删除所有得记录
            new ArrayList<>(bsMxbFysMap.keySet()).forEach(this.baseMapper::deleteById);
            return "保存成功";
        }
        for (BsMxbFysVo vo : vos) {
            validate(vo);
            String id = vo.getId();
            if (id == null || !bsMxbFysMap.containsKey(id)) {
                //新增去重
               /*  List<BsMxbFys> bsMxbFysks = this.baseMapper.selectList(new LambdaQueryWrapper<BsMxbFys>()
                        .eq(StrUtil.isNotBlank("daId"), BsMxbFys::getDaId, daId)
                        .eq(StrUtil.isNotBlank(vo.getMxjbywzl()), BsMxbFys::getMxjbywzl, vo.getMxjbywzl())
                        .eq(StrUtil.isNotBlank(vo.getYwmc()), BsMxbFys::getYwmc, vo.getYwmc())
                        .eq(BsMxbFys::getDelFlag, "0"));
                if (bsMxbFysks != null && !bsMxbFysks.isEmpty()) {
                    throw new ParameterException("该档案已存在该慢性病记录");
                } */
                //执行新增操作
                BsMxbFys po = BeanUtil.toBean(vo, BsMxbFys.class);
                po.setId(IdUtil.objectId());
                po.setDaId(daId);
                po.init();
                save(po);
            } else {
                //执行修改操作
                BsMxbFys existingPo = bsMxbFysMap.get(id);
                if (existingPo == null) {
                    throw new ParameterException("该慢性病记录不存在");
                }
                BeanUtil.copyProperties(vo, existingPo);
                existingPo.initByUpdate();
                updateById(existingPo);
                //从map中移除已经处理的记录
                bsMxbFysMap.remove(id);
            }
            //处理删除操作，剩余在map中的记录为需要删除得记录
            if (!bsMxbFys.isEmpty()) {
                new ArrayList<>(bsMxbFysMap.keySet()).forEach(this.baseMapper::deleteById);
            }
        }
        return "保存成功";
    }

    /**
     * 验证对象
     *
     * @param vo 提交参数
     */
    private void validate(BsMxbFysVo vo) {
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
    public PageData<BsMxbFysVo> findByPage(BsMxbFysSearchVo search) {
        return PageData.of(baseMapper.findByPage(search));
    }

    /**
     * 通过档案ID查询病史_慢性病服药史
     */
    public List<BsMxbFysVo> getBsMxbFysDataByDaId(String daId) {
        List<BsMxbFysVo> results = new ArrayList<>();
        if (StringUtils.isEmpty(daId)) {
            throw new IllegalArgumentException("档案id号不能为空");
        }
        List<BsMxbFys> bsMxbFys = bsMxbFysMapper.findBsMxbFysDataByDaId(daId);
        if (bsMxbFys == null || bsMxbFys.isEmpty()) {
            return results;
        }
        for (BsMxbFys bsMxbFy : bsMxbFys) {
            BsMxbFysVo bsMxbFysVo = new BsMxbFysVo();
            //慢性疾病药物种类字典值转换
//            bsMxbFy.setMxjbywzl(getDictName("MXJBYWZL",bsMxbFy.getMxjbywzl()));
            BeanUtils.copyProperties(bsMxbFy, bsMxbFysVo);
            results.add(bsMxbFysVo);
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
