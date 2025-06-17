package com.shdata.health.gzss.sys.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 随访服药史  搜索VO
 *
 * @author dwt
 * @date 2024-07-23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfFysSearchVo {
    /** 档案ID */
    private String daId;
    /** 开处方医疗机构 */
    private String cfyljg;

}
