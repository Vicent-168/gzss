package com.shdata.health.gzss.common.dict;

import com.shdata.health.common.dict.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DictMapper {

    /**
     * 通过字典类型查询字段数据
     *
     * @param type
     * @return
     */
    List<Dict> findDictByType(@Param("type") String type);

    /*// 新增Area查询方法
    Dict findAreaByCode(@Param("areaCode") String areaCode);

    // 新增Yljg查询方法
    Dict findYljgByCode(@Param("yljgCode") String yljgCode);

    // 新增User查询方法
    Dict findUserById(@Param("userId") String userId);*/

    String findDictByTypeAndCode(@Param("zdlx") String zdlx, @Param("bm") String bm);

    Dict findJyxmByCode(@Param("jyxmCode") String jyxmCode);

    List<Dict> listDictByType(@Param("type") String type);

    Dict findByTypeAndCode(@Param("type") String type, @Param("code") String code);

}
