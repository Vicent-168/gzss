package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shdata.health.common.annotation.DataScope;
import com.shdata.health.gzss.sys.entity.SfJbwz;
import com.shdata.health.gzss.sys.vo.SfJbwzSearchVo;
import com.shdata.health.gzss.sys.vo.SfJbwzVo;
import com.shdata.health.gzss.sys.vo.SfyljbwzVo;
import com.shdata.health.gzss.sys.vo.resp.SfJbwzDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 疾病问诊Mapper接口
 *
 * @author dwt
 * @date 2024-07-22
 */
@Mapper
@Repository
public interface SfJbwzMapper extends BaseMapper<SfJbwz> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<SfJbwzVo> findByPage(SfJbwzSearchVo search);
    /**
     * 随访一览-疾病问诊多条件查询
     */
//    List<SfJbwzDto> findJbwzDataByCriterIas(Page<SfJbwzDto> page, @Param("keyword") String keyword,
//                                            @Param("beginDate") Date beginDate,
//                                            @Param("endDate") Date endDate,
//                                            @Param("wzyljg") String wzyljg,
//                                            @Param("zzlist") List<String> zzlist,
//                                            @Param("xfgz") String xfgz);
    /**
     * 通过档案ID和问诊日期查询疾病问诊VO
     */
    SfJbwzVo findJbwzDataByDaIdandDate(SfyljbwzVo vo);


    IPage<SfJbwzDto> findJbwzDataByCriteria(SfJbwzSearchVo vo);
}
