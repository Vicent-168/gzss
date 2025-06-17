package com.shdata.health.gzss.sys.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 检验指标配置表  VO
 *
 * @author dwt
 * @date 2024-07-18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class JyzlVo implements Serializable {

    /** 检验种类 */
    @NotBlank
    private String code;

}
