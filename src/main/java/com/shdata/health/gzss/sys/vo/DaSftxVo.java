package com.shdata.health.gzss.sys.vo;

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
 * 档案随访提醒  VO
 *
 * @author 丁文韬
 * @date 2024-08-06
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaSftxVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;
    /** 档案ID */
    @NotBlank
    private String daId;
    /** 提醒内容 */
    @NotBlank
    private String txnr;
    /** 最新时间 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date zxsj;
    /** 提醒备注 */
    @NotBlank
    private String txbz;
}
