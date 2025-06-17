package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.utils.ValueUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 个人基本情况的搜索vo
 *
 * @author dwt
 * @date 2024-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PersonalSearchVo {
    /** 用于模糊查询：医保卡号/姓名/身份证号 */
    private String keyword;

    public String getKeyword() {
        return ValueUtil.like(keyword);
    }
}
