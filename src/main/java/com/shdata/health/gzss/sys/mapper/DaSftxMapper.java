package com.shdata.health.gzss.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.common.annotation.DataScope;
import com.shdata.health.gzss.sys.entity.DaSftx;
import com.shdata.health.gzss.sys.vo.DaSftxSearchVo;
import com.shdata.health.gzss.sys.vo.DaSftxVo;
import com.shdata.health.gzss.sys.vo.resp.DaSftxDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 档案随访提醒Mapper接口
 *
 * @author 丁文韬
 * @date 2024-08-06
 */
@Mapper
@Repository
public interface DaSftxMapper extends BaseMapper<DaSftx> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<DaSftxVo> findByPage(DaSftxSearchVo search);
    /**
     * 随访提醒——多条件的列表分页查询查询
     */
    IPage<DaSftxDto> findSftxDataByCriterias(DaSftxSearchVo vo);
}
