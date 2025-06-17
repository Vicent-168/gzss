package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.base.PageSearch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 病史_慢性病服药史  搜索VO
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BsMxbFysSearchVo extends PageSearch<BsMxbFysVo> {


    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("id", "da_id", "mxjbywzl", "ywmc");
    }
}
