package com.shdata.health.gzss.common.vo;

import com.shdata.health.common.base.PageSearch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 检验指标配置表  搜索VO
 *
 * @author dwt
 * @date 2024-07-18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class JyxmSetSearchVo extends PageSearch<JyxmSetVo> {


    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("id", "jy_xmdm", "jy_xmmc", "big_code", "sex_type", "age_from", "age_to", "sort", "dw", "type", "format1", "round", "format2", "jy_ckz", "from_value", "from_sign", "to_value", "to_sign", "init_js", "change_js", "bz", "create_date", "update_date");
    }
}
