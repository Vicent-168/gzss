package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.base.PageSearch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 病史既往骨折史  搜索VO
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BsJwgzsSearchVo extends PageSearch<BsJwgzsVo> {


    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("id", "da_id", "sj", "bw", "yy");
    }
}
