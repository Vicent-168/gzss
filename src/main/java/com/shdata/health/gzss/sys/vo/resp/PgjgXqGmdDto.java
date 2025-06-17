package com.shdata.health.gzss.sys.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * 评估结果骨密度评估详情  VO
 *
 * @author dwt
 * @date 2024-07-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgjgXqGmdDto   {

    /** 主键ID */
    private String pgId;
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
    //起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmdJcks;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  gmdJcjs;
   //骨密度显示数，默认为5
    private Long gmdXss ;
   //骨密度开始记录数，默认为1
    private Long gmdKsjls ;

}
