package com.shdata.health.gzss.sys.vo.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 随访_血生化检查提示
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TsB0 {
    //type类型TS
    private String TS;
    //01   02
    private String CODE;
    // 01 对应正常  02 对应异常
    private String NAME;

}
