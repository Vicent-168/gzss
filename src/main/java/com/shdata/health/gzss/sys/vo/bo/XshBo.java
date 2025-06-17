package com.shdata.health.gzss.sys.vo.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 评估结果-血生化传参
 *
 * @author dwt
 * @date 2024-07-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class XshBo {
    //血生化检验指标的code
    private String code;
    //血生化检验指标的显示数
    private Long xss;
    //检验指标开始记录数
    private Long ksjls;


}
