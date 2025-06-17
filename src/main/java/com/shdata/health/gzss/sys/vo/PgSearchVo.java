package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 评估  搜索VO
 *
 * @author dwt
 * @date 2024-07-23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgSearchVo {
    /** 档案ID */
    private String daId;
    /** 评估日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date pgrq;
}
