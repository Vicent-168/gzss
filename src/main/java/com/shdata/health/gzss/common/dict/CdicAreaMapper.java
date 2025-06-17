package com.shdata.health.gzss.common.dict;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CdicAreaMapper {

    String getName(@Param("area") String area);
}
