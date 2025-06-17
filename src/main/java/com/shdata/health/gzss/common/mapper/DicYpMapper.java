package com.shdata.health.gzss.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shdata.health.gzss.common.entity.DicYp;
import com.shdata.health.gzss.common.vo.DicYpSearchVo;
import com.shdata.health.gzss.common.vo.DicYpVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 药品目录Mapper接口
 *
 * @author dwt
 * @date 2024-07-19
 */
@Mapper
@Repository
public interface DicYpMapper extends BaseMapper<DicYp> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    List<DicYpVo> findList(DicYpSearchVo search);
    /**
     * 通过药物代码查询药品目录
     */
    List<DicYpVo> getList();
    /**
     * 根据药物代码查询药品信息
     */
    DicYpVo getYpByYwdm(@Param("ywdm") String ywdm);
}
