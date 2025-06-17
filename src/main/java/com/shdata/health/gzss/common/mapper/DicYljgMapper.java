package com.shdata.health.gzss.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.gzss.common.entity.DicYljg;
import com.shdata.health.gzss.common.vo.DicYljgSearchVo;
import com.shdata.health.gzss.common.vo.DicYljgVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 医疗机构Mapper接口
 *
 * @author 丁文韬
 * @date 2024-11-25
 */
@Mapper
@Repository
public interface DicYljgMapper extends BaseMapper<DicYljg> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     */
    IPage<DicYljgVo> findByPage(DicYljgSearchVo search);
    /**
     * 获取医疗机构代码及其名称
     */
    List<DicYljgVo> findList();
}
