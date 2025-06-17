package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.annotation.DataScope;
import com.shdata.health.gzss.sys.entity.BsJwgzs;
import com.shdata.health.gzss.sys.vo.BsJwgzsSearchVo;
import com.shdata.health.gzss.sys.vo.BsJwgzsVo;
import com.shdata.health.gzss.sys.vo.JwgzsSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 病史既往骨折史Mapper接口
 *
 * @author dwt
 * @date 2024-07-19
 */
@Mapper
@Repository
public interface BsJwgzsMapper extends BaseMapper<BsJwgzs> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<BsJwgzsVo> findByPage(BsJwgzsSearchVo search);

    List<BsJwgzsVo> selectByDaIdWithPagination(@Param("daId") String daId);

    long countByDaId(@Param("daId") String daId);
    /**
     * 通过档案ID和诊断日期查询病史既往骨折史
     */
    List<BsJwgzsVo> selectByDaIdandZdrq(JwgzsSearchVo vo);
}
