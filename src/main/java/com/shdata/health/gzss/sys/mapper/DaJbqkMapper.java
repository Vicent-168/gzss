package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.gzss.sys.entity.DaJbqk;
import com.shdata.health.gzss.sys.vo.DaJbqkRespVo;
import com.shdata.health.gzss.sys.vo.DaJbqkSearchVo;
import com.shdata.health.gzss.sys.vo.PersonalInfoVo;
import com.shdata.health.gzss.sys.vo.PersonalSearchVo;
import com.shdata.health.gzss.sys.vo.resp.DaJbqkVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 档案基本情况Mapper接口
 *
 * @author xgb
 * @date 2024-07-10
 */
@Mapper
@Repository
public interface DaJbqkMapper extends BaseMapper<DaJbqk> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<DaJbqkVo> findByPage(DaJbqkSearchVo search);

//    /**
//     * 根据档案id查询基本表的信息
//     *
//     * @param
//     * @return 返回分页数据
//     * TODO 需要确认权限字段
//     */
//    IPage<DaJbqkVo> getDaJbqkVoByDaId(@Param("daid") String daid);


    /**
     * 根据 keyword 查询
     *
     * @param keyword 传入的 keyword
     * @return 查询结果
     */
    List<DaJbqk> selectByKeyword(@Param("keyword") String keyword);

    /**
     * 根据唯一 keyword 查询
     *
     * @param keyword 传入的唯一 keyword
     * @return 查询结果
     */
    DaJbqk selectByUniqueKeyword(@Param("keyword") String keyword);

    DaJbqkVo findByDaId(@Param("daId") String daId);

    /**
     * 根据病员号（对应医保卡号）查询档案id
     */
    String getArchiveIdByPatientNumber(@Param("patientNumber") String patientNumber);

    /**
     * 档案管理-档案一览
     */
//    List<DaJbqkRespVo> findDataByCriterias(Page<DaJbqkRespVo> page,
//                                           @Param("keyword") String keyword,
//                                           @Param("glyljg") String glyljg,
//                                           @Param("startDate") Date startDate,
//                                           @Param("endDate") Date endDate,
//                                           @Param("zdlist") List<String> zdlist,
//                                           @Param("daztlist") List<String> daztlist,
//                                           @Param("sfzsid") String sfzsid,
//                                           @Param("jtysid") String jtysid);
    IPage<DaJbqkRespVo> findDataByCriterias(DaJbqkSearchVo vo);

    /**
     * 通过身份证号查询档案基本情况
     */
    DaJbqkVo findBySfzh(@Param("sfzh") String sfzh);
    /**
     * 通过档案ID查询个人简单基本情况
     */
    PersonalInfoVo findPsersonalInfoByDaId(@Param("daId") String daId);
    /**
     * 通过姓名/身份证号/社保卡号查询个人简单基本情况
     */
    PersonalInfoVo getPsersonalInfoBKeyword(PersonalSearchVo vo);

}
