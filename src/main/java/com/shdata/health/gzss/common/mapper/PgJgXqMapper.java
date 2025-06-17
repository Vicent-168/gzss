package com.shdata.health.gzss.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.gzss.common.entity.PgJgXq;
import com.shdata.health.gzss.common.vo.PgJgXqSearchVo;
import com.shdata.health.gzss.common.vo.PgJgXqVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 评估_结果_详情Mapper接口
 *
 * @author 丁文韬
 * @date 2024-07-25
 */
@Mapper
@Repository
public interface PgJgXqMapper extends BaseMapper<PgJgXq> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<PgJgXqVo> findByPage(PgJgXqSearchVo search);
    // 插入评估结果详情表
    void insertPgJgXq(PgJgXq pgJgXq);
}
