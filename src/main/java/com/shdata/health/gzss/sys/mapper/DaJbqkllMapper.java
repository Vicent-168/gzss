package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.gzss.sys.entity.DaJbqkll;
import com.shdata.health.gzss.sys.vo.DaJbqkllSearchVo;
import com.shdata.health.gzss.sys.vo.resp.DaJbqkllVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 档案_基本情况履历表Mapper接口
 *
 * @author dwt
 * @date 2024-07-12
 */
@Mapper
@Repository
public interface DaJbqkllMapper extends BaseMapper<DaJbqkll> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<DaJbqkllVo> findByPage(DaJbqkllSearchVo search);

    void insertInfo(DaJbqkll daJbqkll);
}
