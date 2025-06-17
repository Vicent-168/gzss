package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shdata.health.gzss.sys.entity.SfGmdjc;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.resp.DaSftxDto;
import com.shdata.health.gzss.sys.vo.resp.PgjgGmdDto;
import com.shdata.health.gzss.sys.vo.resp.PgjgXqGmdDto;
import com.shdata.health.gzss.sys.vo.resp.SfGmdJcDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 随访_骨密度检查Mapper接口
 *
 * @author dwt
 * @date 2024-07-16
 */
@Mapper
@Repository
public interface SfGmdjcMapper extends BaseMapper<SfGmdjc> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<SfGmdjcVo> findByPage(SfGmdjcSearchVo search);

    /**
     * 随访一览_骨密度检查
     *
     * @param
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
//    List<SfGmdJcDto> searchSfGmdjc(Page<SfGmdJcDto> page,
//                                   @Param("keyword") String keyword,
//                                   @Param("jcyljg") String jcyljg,
//                                   @Param("beginDate") Date beginDate,
//                                   @Param("endDate") Date endDate,
//                                   @Param("buWeiCodes") List<String> buWeiCodes,
//                                   @Param("tzCodes") List<String> tzCodes,
//                                   @Param("yfCode") String yfCode);

    /**
     * 根据档案ID分页查询骨密度历史数据
     *
     * @return 查询结果
     */
    IPage<SfGmdjcVo> selectByDaIdWithPagination(SfGmdjcSearchVo vo);

    /**
     * 根据档案ID查询总记录数
     *
     * @param daId 档案ID
     * @return 总记录数
     */
    long countByDaId(@Param("daId") String daId);

    /**
     * 评估结果-新建骨密度检查
     */
    IPage<PgjgGmdDto> getPgjgGmdDataByCriterias(@Param("vo") PgjgGmdSearchVo vo);

//    /**
//     * 执行骨密度检查的分页查询
//     *
//     * @param page 分页对象
//     * @param sql  动态生成的 SQL 查询语句
//     * @return 查询结果
//     */
//    List<SfGmdJcDto> searchSfGmdjc(@Param("page") Page<SfGmdJcDto> page,
//                                   @Param("sql") String sql,
//                                   @Param("search") SfGmdjcSearchVo search
//    );
//
//    long countSfGmdjc(@Param("search") SfGmdjcSearchVo search);

//    IPage<SfGmdJcDto> searchSfGmdjc(
//                                   @Param("keyword") String keyword,
//                                   @Param("jcyljg") String jcyljg,
//                                   @Param("beginDate") Date beginDate,
//                                   @Param("endDate") Date endDate,
//                                   @Param("yfCode") String yfCode,
//                                   @Param("bwCodes") List<String> bwCodes,
//                                   @Param("tzCodes") List<String> tzCodes);

//    /**
//     * 随访一览-骨密度检查与条件下的查询
//     */
//
//    IPage<SfGmdJcDto> searchSfGmdjc1(
//            Page<SfGmdJcDto> page,
//            @Param("keyword") String keyword,
//            @Param("jcyljg") String jcyljg,
//            @Param("beginDate") Date beginDate,
//            @Param("endDate") Date endDate,
//            @Param("sqlList") List<String> sqlList);

    /**
     * 随访一览-骨密度检查与条件下的查询
     */

    IPage<SfGmdJcYlo> searchSfGmdjc1(@Param("search") SfGmdjcSearchVo search,
                                     @Param("sqlList") List<String> sqlList);
    IPage<SfGmdJcYlo> searchSfGmdjc3(@Param("search") SfGmdjcSearchVo search);


//    /**
//     * 随访一览-骨密度检查或条件下的查询
//     */
//    IPage<SfGmdJcDto> searchSfGmdjc2(
//            Page<SfGmdJcDto> page,
//            @Param("keyword") String keyword,
//            @Param("jcyljg") String jcyljg,
//            @Param("beginDate") Date beginDate,
//            @Param("endDate") Date endDate,
//            @Param("sqlList") List<String> sqlList);

    /**
     * 随访一览-骨密度检查或条件下的查询
     */
    IPage<SfGmdJcYlo> searchSfGmdjc2(@Param("search") SfGmdjcSearchVo search,
                                     @Param("sqlList") List<String> sqlList);

    /**
     * 评估结果-详情骨密度检查结果
     */
    IPage<PgjgXqGmdDto> findPgjgXqGmdDataByCriterias( PgjgXqGmdSearchVo vo);

    PgjgXqGmdVo getPaginationInfoByDaId(@Param("daId") String daId);
    /**
     * 随访提醒-骨密度检查超期提醒
     */
    List<SfGmdjctxVo> findSftxGmdDataByCriterias(DaSftxSearchVo vo);
    /**
     * 通过档案ID查询随访最新骨密度信息
     */
    SfGmdjcVo findLatestGmdCheckDataByDaId(@Param("daId") String daId);
    /**
     * 通过档案ID和诊断日期查询随访骨密度信息
     */
    SfGmdjcVo findGmdCheckDataBydaIdandZdrq(GmdlySearchvo vo);
}
