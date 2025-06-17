package com.shdata.health.gzss.common.vo;

import com.shdata.health.common.base.PageSearch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 评估_结果_详情  搜索VO
 *
 * @author 丁文韬
 * @date 2024-07-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgJgXqSearchVo extends PageSearch<PgJgXqVo> {


    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("xq_id", "pg_id", "da_id", "jyxm", "xss", "ksjls");
    }
}
