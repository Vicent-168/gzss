package com.shdata.health.gzss.sys.vo.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 诊断
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ZdBo {
    //字典诊断指标
    private String ZD;
    //01 02 03 04 05 99
    private String CODE;
    //骨质疏松  骨量减少 骨质正常 骨质疏松伴病理性骨折 骨量减少伴病理性骨折 其他
    private String NAME;
}
