package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
/**
 * 血生化详情  搜索VO
 *
 * @author 丁文韬
 * @date 2024-07-26
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class XshYlSearchVo {
    /** 档案id */
    private String daId;
    //转诊时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jyrq;
}
