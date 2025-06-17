package com.shdata.health.gzss.sys.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.gzss.sys.vo.bo.XshBoS;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 血生化检查  VO
 *
 * @author 丁文韬
 * @date 2024-07-29
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ExcelIgnoreUnannotated
public class XshxqVo implements Serializable {
    /** 档案ID */
    private String daId;
    /** 评估ID */
    private String pgId;
    //起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  endDate;
    /** 评估血生化指标对象集合 */
    private List<XshBoS> xshBoos;


}
