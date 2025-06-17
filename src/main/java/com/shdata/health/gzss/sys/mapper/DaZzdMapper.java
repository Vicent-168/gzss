package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.annotation.DataScope;
import com.shdata.health.gzss.sys.entity.DaZzd;
import com.shdata.health.gzss.sys.vo.DaZzdSearchVo;
import com.shdata.health.gzss.sys.vo.DaZzdVo;
import com.shdata.health.gzss.sys.vo.ZzdSearchVo;
import com.shdata.health.gzss.sys.vo.resp.DaZzdDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 档案_转诊单Mapper接口
 *
 * @author 丁文韬
 * @date 2024-07-26
 */
@Mapper
@Repository
public interface DaZzdMapper extends BaseMapper<DaZzd> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<DaZzdVo> findByPage(DaZzdSearchVo search);

    int insertDaZzd(DaZzd daZzd);

//    List<DaZzdDto> findZzdDataByCriterias(@Param("keyword") String keyword,
//                                          @Param("beginDate")Date beginDate,
//                                          @Param("endDate")Date endDate,
//                                          @Param("glyljg")String glyljg,
//                                          @Param("zzztlist")List<String> zzztlist,
//                                          @Param("glgtlist")List<String> glgtlist,
//                                          @Param("zzlblist")List<String> zzlblist,
//                                          @Param("zcyy")String zcyy,
//                                          @Param("zzrq")String zzrq,
//                                          @Param("offset")long offset,
//                                          @Param("limit")long limit);
    IPage<DaZzdDto> findZzdDataByCriterias(DaZzdSearchVo vo);
    /**
     * 通过档案ID查询转诊信息
     */
    DaZzdVo findZzdByDaId(@Param("daId") String daId);
    /**
     * 通过档案ID和转诊日期查询转诊信息
     */
    DaZzdVo findZzdByDaIdandZzrq(ZzdSearchVo vo);
}
