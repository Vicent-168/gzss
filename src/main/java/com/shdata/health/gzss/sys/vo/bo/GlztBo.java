package com.shdata.health.gzss.sys.vo.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 管理状态
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GlztBo {
    //管理状态TYPE
    private String GLZT;
    // 1 2 9
    private String code;
    //在管 暂存 转归
    private String name;

}
