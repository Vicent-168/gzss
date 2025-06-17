package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.annotation.DataScope;
import com.shdata.health.gzss.sys.entity.BsMxbFys;
import com.shdata.health.gzss.sys.vo.BsMxbFysSearchVo;
import com.shdata.health.gzss.sys.vo.BsMxbFysVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 病史_慢性病服药史Mapper接口
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Mapper
@Repository
public interface BsMxbFysMapper extends BaseMapper<BsMxbFys> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<BsMxbFysVo> findByPage(BsMxbFysSearchVo search);
    /**
     * 通过档案ID查询病史_慢性病服药史
     */
    List<BsMxbFys> findBsMxbFysDataByDaId(@Param("daId") String daId);
}
