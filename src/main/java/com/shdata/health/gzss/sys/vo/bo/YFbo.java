package com.shdata.health.gzss.sys.vo.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 与或条件选择
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class YFbo {
    //逻辑字段
    private String YH;
    //1  /  2
    private String CODE;
    //1表示与用and连接逻辑 2表示或用or逻辑连接
    private String NAME;
}
