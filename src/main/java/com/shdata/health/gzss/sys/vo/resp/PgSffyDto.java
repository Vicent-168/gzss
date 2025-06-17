package com.shdata.health.gzss.sys.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 评估随访服药  PgSffyDto
 *
 * @author dwt
 * @date 2024-07-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgSffyDto {
    /** 主键ID */
    private String id;
    /** 档案ID */
    private String daId;
    /** 药品名称 */
    private String ypmc;
    /** 处方日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date cfrq;
    /** 药物调整情况 01延续 02 新增 03停用 */
    private String tzqk;
}
