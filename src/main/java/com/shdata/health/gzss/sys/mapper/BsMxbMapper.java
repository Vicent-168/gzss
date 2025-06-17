package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.annotation.DataScope;
import com.shdata.health.gzss.sys.entity.BsMxb;
import com.shdata.health.gzss.sys.vo.BsMxbSearchVo;
import com.shdata.health.gzss.sys.vo.BsMxbVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 病史慢性病Mapper接口
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Mapper
@Repository
public interface BsMxbMapper extends BaseMapper<BsMxb> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<BsMxbVo> findByPage(BsMxbSearchVo search);
    /**
     * 通过档案ID查询病史慢性病
     */
    List<BsMxb> findMxbDataByDaId(@Param("daId") String daId);
}
