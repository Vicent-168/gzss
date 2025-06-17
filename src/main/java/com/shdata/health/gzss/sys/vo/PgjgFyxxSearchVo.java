package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 评估_结果  新建服药信息搜索VO
 *
 * @author dwt
 * @date 2024-07-23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgjgFyxxSearchVo {
    /** 档案id */
    private String daId;
    //起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  endTime;
}
