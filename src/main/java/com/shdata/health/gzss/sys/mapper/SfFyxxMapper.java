package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.gzss.sys.entity.SfFyxx;
import com.shdata.health.gzss.sys.vo.SfFysSearchVo;
import com.shdata.health.gzss.sys.vo.SfFyxxSearchVo;
import com.shdata.health.gzss.sys.vo.SfFyxxVo;
import com.shdata.health.gzss.sys.vo.SfFyxxylSearchVo;
import com.shdata.health.gzss.sys.vo.resp.SfFyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 随访服药信息Mapper接口
 *
 * @author dwt
 * @date 2024-07-19
 */
@Mapper
@Repository
public interface SfFyxxMapper extends BaseMapper<SfFyxx> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<SfFyxxVo> findByPage(SfFyxxSearchVo search);

    //根据档案id查询随访服药史信息
    List<SfFyxxVo> findSffsDataByDaId(SfFysSearchVo vo);

    /**
     * 随访一览-服药信息的多条件查询功能
     */
    IPage<SfFyDto> findSfFyDataByCriterias(SfFyxxSearchVo vo);

    /**
     * 通过档案ID查询建档前服药信息
     */
    List<SfFyxxVo> fetchHistoryFyxxDataByDaId(@Param("daId") String daId);
    /**
     * 通过档案ID查询建最新服药信息
     */
    List<SfFyxxVo> fetchLatestFyxxDataByDaId(@Param("daId") String daId);
    /**
     * 通过档案ID和开方日期查询服药信息
     */
    List<SfFyxxVo> fetchFyxxDataByDaIdandDate(SfFyxxylSearchVo vo);
}
