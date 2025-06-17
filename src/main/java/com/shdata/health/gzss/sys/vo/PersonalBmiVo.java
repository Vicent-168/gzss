package com.shdata.health.gzss.sys.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 评估_结果  搜索VO
 *
 * @author dwt
 * @date 2024-07-23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PersonalBmiVo {
    /** 身份证号 */
    private String sfzh;
    /** 身高 */
    private BigDecimal sg;
    /** 体重 */
    private BigDecimal tz;
}
