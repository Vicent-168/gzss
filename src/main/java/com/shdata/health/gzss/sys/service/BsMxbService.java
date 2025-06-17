package com.shdata.health.gzss.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.utils.StringUtils;
import com.shdata.health.gzss.common.dict.DictService;
import com.shdata.health.gzss.sys.entity.BsMxb;
import com.shdata.health.gzss.sys.mapper.BsMxbMapper;
import com.shdata.health.gzss.sys.vo.BsMxbSearchVo;
import com.shdata.health.gzss.sys.vo.BsMxbVo;
import com.shdata.health.common.base.BaseService;
import com.shdata.health.common.base.PageData;
import com.shdata.health.common.exception.ParameterException;
import com.shdata.health.gzss.sys.vo.bo.BsMxbBo;
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
 * 病史慢性病  Service服务
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Service
@Transactional(readOnly = true)
public class BsMxbService extends BaseService<BsMxbMapper, BsMxb> {
    @Autowired
    private BsMxbMapper bsMxbMapper;

    @Autowired
    private DictService dictService;

    /**
     * 保存或更新
     *
     * @param bo 提交参数
     * @return
     */
    @Transactional
    public String saveOrUpdate(BsMxbBo bo) {
        String daId = bo.getDaId();
        // 获取当前数据库中与该档案ID相关的记录
        List<BsMxb> bsMxbs = this.baseMapper.selectList(new LambdaQueryWrapper<BsMxb>().eq(BsMxb::getDaId, daId).eq(BsMxb::getDelFlag, "0"));
        Map<String, BsMxb> bsMxbMap = new HashMap<>();
        if (bsMxbs != null && !bsMxbs.isEmpty()) {
            bsMxbMap=bsMxbs.stream().collect(Collectors.toMap(BsMxb::getId, Function.identity()));
        }
        List<BsMxbVo> vos = bo.getBsMxbVoList();
        if (vos == null || vos.isEmpty()) {
            //删除所有得记录
            new ArrayList<>(bsMxbMap.keySet()).forEach(this.baseMapper::deleteById);
            return "保存成功";
        }
        for (BsMxbVo vo : vos) {
            validate(vo);
            String id=vo.getId();
            if (id == null || !bsMxbMap.containsKey(id)) {
                //新增去重
               /*  List<BsMxb> bsMxbks = this.baseMapper.selectList(new LambdaQueryWrapper<BsMxb>()
                        .eq(StrUtil.isNotBlank(daId), BsMxb::getDaId, daId)
                        .eq(StrUtil.isNotBlank(vo.getMxjb()), BsMxb::getMxjb, vo.getMxjb())
                        .eq(vo.getQzny()!=null, BsMxb::getQzny, vo.getQzny())
                        .eq(BsMxb::getDelFlag, "0"));
                if (bsMxbks != null && !bsMxbks.isEmpty()) {
                    throw new ParameterException("该档案已存在该慢性病记录");
                } */
                //执行新增操作
                BsMxb po = BeanUtil.toBean(vo, BsMxb.class);
                po.setId(IdUtil.objectId());
                po.setDaId(daId);
                po.init();
                save(po);
            } else {
                //执行修改操作
                BsMxb existingPo = bsMxbMap.get(id);
                if (existingPo == null) {
                    throw new ParameterException("该慢性病记录不存在");
                }
                BeanUtil.copyProperties(vo, existingPo);
                existingPo.initByUpdate();
                updateById(existingPo);
                //从map中移除已经处理的记录
                bsMxbMap.remove(id);
            }
            }
        //处理删除操作，剩余在map中的记录为需要删除得记录
        if (!bsMxbs.isEmpty()) {
            new ArrayList<>(bsMxbMap.keySet()).forEach(this.baseMapper::deleteById);
        }
        return "保存成功";
    }

    /**
     * 验证对象
     *
     * @param vo 提交参数
     */
    private void validate(BsMxbVo vo) {
        if (vo == null) {
            throw new ParameterException("参数不能为空");
        }
    }

//    /**
//     * 查询分页数据
//     *
//     * @param search 分页查询对象
//     * @return
//     */
//    public PageData<BsMxbVo> findByPage(BsMxbSearchVo search) {
//        return PageData.of(baseMapper.findByPage(search));
//    }

    /**
     * 通过档案ID查询病史慢性病
     */
    public List<BsMxbVo> getMxbData(String daId) {
        List<BsMxbVo> results = new ArrayList<>();
        if (StringUtils.isEmpty(daId)) {
            throw new IllegalArgumentException("档案号不能为空！");
        }
        List<BsMxb> bsMxbs = bsMxbMapper.findMxbDataByDaId(daId);
        if (bsMxbs == null || bsMxbs.isEmpty()) {
            return results;
        }
        for (BsMxb bsMxb : bsMxbs) {
            BsMxbVo bsMxbVo = new BsMxbVo();
//            //慢性基本字典值转换
//            bsMxb.setMxjb(getDictName("MXJB",bsMxb.getMxjb()));
            BeanUtils.copyProperties(bsMxb, bsMxbVo);
            results.add(bsMxbVo);
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
