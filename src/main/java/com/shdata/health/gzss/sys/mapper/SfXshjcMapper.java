package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shdata.health.gzss.sys.entity.SfXshjc;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.resp.PgjgXshVo;
import com.shdata.health.gzss.sys.vo.resp.SfXshjcDto;
import com.shdata.health.gzss.sys.vo.resp.SfXshjcVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 随访_血生化检查Mapper接口
 *
 * @author dwt
 * @date 2024-07-13
 */
@Mapper
@Repository
public interface SfXshjcMapper extends BaseMapper<SfXshjc> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<SfXshjcVo> findByPage(SfXshjcSearchVo search);

    /**
     * 查询最新血生化检查数据
     *
     * @param daId 档案id
     * @return 返回最新血生化数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")

    List<SfXshjcVo> findLatestBiochemicalCheckData(@Param("daId") String daId);

    /**
     * 根据档案id查询血生化检查数据
     *
     * @param daid 档案id
     * @return 返回血生化数据
     * TODO 需要确认权限字段
     */
    List<SfXshjcVo> findByDaid(@Param("daid") String daid);

    /**
     * 根据档案id和jyrq检验日期查询血生化检查数据
     *
     * @param daid 档案id
     * @return 返回血生化数据
     * TODO 需要确认权限字段
     */
    List<SfXshjcVo> findByDaidSortByJyrq(@Param("daid") String daid);
//    /**
//     * 随访一览 血生化检查数据
//     *
//     *
//     * TODO 需要确认权限字段
//     */
//    List<SfXshjcDto> searchSfXshjc(
//                                   @Param("keyword") String keyword,
//                                   @Param("jcyljg") String jcyljg,
//                                   @Param("beginDate") Date beginDate,
//                                   @Param("endDate") Date endDate,
//                                   @Param("jcxmCodes") List<String> jyxmCodes,
//                                   @Param("tsCode") String tsCode,
//                                   @Param("yfCode") String yfCode);

    /**
     * 随访一览 血生化检查数据
     * <p>
     * <p>
     * TODO 需要确认权限字段
     */
    List<SfXshjcDto> searchSfXshjc(
            @Param("keyword") String keyword,
            @Param("jcyljg") String jcyljg,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate,
            @Param("jcxmCodes") List<String> jyxmCodes,
            @Param("tsCode") String tsCode,
            @Param("yfCode") String yfCode);

    /**
     * 根据条件查询评估结果-血生化检查
     */
    IPage<PgjgXshVo> findPgXshDataByDaidAndJyrq(PgjgXshSearchVo vo);

    /**
     * 根据条件查询评估结果-血生化检查
     */
    void save(List<XshjcVo> cachedDataList);

    /**
     * 计算查询条件下的总记录数
     */
    long countPgXshDataByDaidAndJyrq(@Param("daId") String daId,
                                     @Param("beginDate") Date beginDate,
                                     @Param("endDate") Date endDate,
                                     @Param("jyxmCode") String jyxmCode);

    /**
     * 随访一览-血生化登记检查查询
     */
    List<SfXshjcVo> findLatestByDaId(@Param("daid") String daid);

    /**
     * 随访提醒-血生化超期提醒数据
     */
    List<SfXshjctxVo> findSftxXshDataByCriterias(DaSftxSearchVo vo);

    /**
     * 评估结果-详情血生化检查查询
     */
    IPage<PgjgXshVo> findPgXshXqDataByCriterias(PgjgXshSearchVo vo);

    /**
     * 评估结果-新建血生化检查查询
     */
    IPage<PgjgXshVo> findPgXshXjDataByCriterias(PgjgXshSearchVo vo);

    IPage<SfXshjcDto> searchResults( @Param("searchVo") SfXshjcSearchVo searchVo);
    /**
     * 通过档案ID和检查日期查询随访_血生化检查VO
     */
    List<SfXshjcVo> fetchBiochemicalCheckDataBydaIdandCjrq(XshYlSearchVo vo);
}
