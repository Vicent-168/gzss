package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.annotation.DataScope;
import com.shdata.health.gzss.sys.entity.BsYjqk;
import com.shdata.health.gzss.sys.vo.BsYjqkSearchVo;
import com.shdata.health.gzss.sys.vo.BsYjqkVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 病史_饮酒情况Mapper接口
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Mapper
@Repository
public interface BsYjqkMapper extends BaseMapper<BsYjqk> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<BsYjqkVo> findByPage(BsYjqkSearchVo search);
    /**
     * 通过档案ID查询病史_饮酒情况
     */
    List<BsYjqk> findYjqkDataByDaId(@Param("daId") String daId);
}
