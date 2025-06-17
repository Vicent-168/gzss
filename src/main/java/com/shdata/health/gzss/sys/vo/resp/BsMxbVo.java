package com.shdata.health.gzss.sys.vo.resp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 病史_慢性病  VO
 *
 * @author dwt
 * @date 2024-07-12
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BsMxbVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;
    /** 档案ID */
    @NotBlank
    private String daId;
    /** 慢性疾病 */
    @NotBlank
    private String mxjb;
    /** 其他慢性疾病 */
    @NotBlank
    private String qtmxjb;
    /** 确诊年月 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date qzny;
}
