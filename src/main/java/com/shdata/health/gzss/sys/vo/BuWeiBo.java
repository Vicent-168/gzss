package com.shdata.health.gzss.sys.vo;

import jakarta.validation.constraints.NotNull;
/**
 *   骨密度检查结果
 *
 * @author dwt
 * @date 2024-07-15
 */
public class BuWeiBo {
    /** L1~L4平均值 */
    @NotNull
    private Long gmdL1L4;

    /** 骨密度全髋 */
    @NotNull
    private Long gmdQh;

    /** 骨密度股骨颈 */
    @NotNull
    private Long gmdGgj;
}
