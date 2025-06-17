package com.shdata.health.gzss.sys.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 评估结果  PgjgGmdDto
 *
 * @author dwt
 * @date 2024-07-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgjgGmdDto {
    /** 主键ID */
    private String id;
    /** 档案ID */
    private String daId;
    /** 评估日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dxaJcrq;
    /** T值L1 */
    private BigDecimal tzL1;
    /** T值L2 */
    private BigDecimal tzL2;
    /** T值L3 */
    private BigDecimal tzL3;
    /** T值L4 */
    private BigDecimal tzL4;
    /** T值L1-L4 */
    private BigDecimal tzL1L4;
    /** T值全髋 */
    private BigDecimal tzQh;
    /** T值股骨颈 */
    private BigDecimal tzGgj;
}
