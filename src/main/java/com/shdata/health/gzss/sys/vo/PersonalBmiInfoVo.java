package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * BMI  VO
 *
 * @author dwt
 * @date 2024-11-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PersonalBmiInfoVo {
    @DictFormat(dictType = DictType.DICT, dictKey = "BMISP")
    private String bmisp;

    private String BMiSz;
}
