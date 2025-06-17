package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 随访骨密度一览  搜索VO
 *
 * @author 丁文韬
 * @date 2024-08-06
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GmdlySearchvo {
    /** 档案ID */
    @NotNull
    private String daId;
    /** 开方日期 */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dxaJcrq;
}
