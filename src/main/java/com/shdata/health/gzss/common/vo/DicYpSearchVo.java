package com.shdata.health.gzss.common.vo;

import com.shdata.health.common.base.PageSearch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 药品目录  搜索VO
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DicYpSearchVo extends PageSearch<DicYpVo> {


    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("id", "ywdm", "dmlx", "yn_yljgdm", "ym_hxm", "yw_spm", "dfl_cd", "jx", "gg", "dw", "ywsytj", "pinyin");
    }
}
