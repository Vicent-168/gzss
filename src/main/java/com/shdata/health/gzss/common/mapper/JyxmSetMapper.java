package com.shdata.health.gzss.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shdata.health.gzss.common.entity.JyxmSet;
import com.shdata.health.gzss.common.vo.JyxmSetSearchVo;
import com.shdata.health.gzss.common.vo.JyxmSetVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 检验指标配置表Mapper接口
 *
 * @author dwt
 * @date 2024-07-18
 */
@Mapper
@Repository
public interface JyxmSetMapper extends BaseMapper<JyxmSet> {

    /**
     * 查询分页数据
     *
     * @param search 分页查询对象
     * @return 返回分页数据
     * TODO 需要确认权限字段
     */
    //@DataScope(column = "")
    IPage<JyxmSetVo> findByPage(JyxmSetSearchVo search);

    /**
     * 根据检验项目代码、性别类型和年龄查询检验指标配置表
     *
     * @param jyXmdm 检验项目代码
     * @param
     * @param
     * @return 符合条件的检验指标配置表列表
     */
    List<JyxmSetVo> findByCriteria(@Param("jyXmdm") String jyXmdm);
    /**
     * 根据检验种类获取检验项目
     */
    List<JyxmSetVo> findJyxmByJyzl(@Param("bigCode") String bigCode);
    /**
     * 根据检验项目名称获取检验种类
     */
    List<JyxmSetVo> findJyzlByJyxm(@Param("jyxmmc")String jyxmmc);
}
