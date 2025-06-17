package com.shdata.health.gzss.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 家庭医生VO
 */
@Data
public class DoctorVo implements Serializable {

    /**
     * 医疗机构代码
     */
    private String yljgdm;
    /**
     * 工号
     */
    private String gh;
    /**
     * 姓名
     */
    private String xm;
    /**
     * 身份证
     */
    private String sfzh;
    /**
     * 手机号
     */
    private String sjhm;
    /**
     * 修改标志：1.有效 2.废弃
     */
    private String xgbz;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ccrksj;

}
