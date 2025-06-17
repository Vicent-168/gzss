package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shdata.health.gzss.sys.entity.PgJg;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.resp.PgJgDto;
import com.shdata.health.gzss.sys.vo.resp.PgjgGmdDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 评估_结果Mapper接口
 *
 * @author dwt
 * @date 2024-07-25
 */
@Mapper
@Repository
public interface PgJgMapper extends BaseMapper<PgJg> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<PgJgVo> findByPage(PgJgSearchVo search);

    /**
     * 评估一览_多条件模糊查询
     */
    IPage<PgJgDto> findPgJgDataByCriteria1(PgJgSearchVo vo);
    IPage<PgJgDto> findPgJgDataByCriteria2(PgJgSearchVo vo);

    IPage<PgJgDto> findPgJgDataByCriteria3(PgJgSearchVo vo);
//    List<PgJgDto> findPgJgDataByCriterias(Page<PgJgDto> page,
//                                          @Param("keyword") String keyword,
//                                          @Param("beginDate") Date beginDate,
//                                          @Param("endDate") Date endDate,
//                                          @Param("glyljg") String glyljg,
//                                          @Param("pgysId") String pgysId,
//                                          @Param("zdlist") List<String> zdlist,
//                                          @Param("glztlist") List<String> glztlist,
//                                          @Param("pgjg") String pgjg);

    void insertPgJg(PgJg pgJg);

//    List<PgjgGmdDto> getPgjgGmdDataByCriterias(PgjgGmdVo vo);
    /**
     * 评估结果-详情血生化检查查询
     */
    PgjgXqVo findPgXshDataBy(PgjgXshSearchVo vo);
    /**
     * 评估结果-详情骨密度分页信息
     */
    PgjgXqGmdVo findPgGmdDataByDaId(@Param("daId") String daId);
    /**
     * 通过档案ID查询评估_结果VO
     */
    PgJgVo findPgJgDataByDaId(String daId);
    /**
     * 通过档案ID和评估日期查询评估_结果VO
     */
    PgJgVo findPgJgDataByDaIdandPgrq(PgSearchVo vo);
    /**
     * 评估一览_多条件模糊查询
     */
    IPage<PgJgDto> findPgJgDataByCriterias(PgJgSearchVo vo);

}
