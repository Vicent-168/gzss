package com.shdata.health.gzss.sys.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class JwgzsSearchVo {
    /** 档案id */
    private String daId;
    /*疾病问诊主键*/
    private String jbwzId;
}
