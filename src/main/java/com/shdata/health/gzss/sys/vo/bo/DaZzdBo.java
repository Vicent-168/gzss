package com.shdata.health.gzss.sys.vo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;

/**
 * 创建转诊单
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaZzdBo {
    @Serial
    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private String id;
    /** 档案ID */
    @NotBlank
    private String daId;
    /** 转出医疗机构 */
    @NotBlank
    private String zcyljg;
    /** 转诊状态 */
    @NotBlank
    private String zzzt;
    /** 转诊类别 */
    @NotBlank
    private String zzlb;
    /** 转入医疗机构 */
    @NotBlank
    private String zryljg;
    /** 转诊日期 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date zzrq;
    /** 转诊医生ID */
    @NotBlank
    private String zzysid;
    /** 转诊医生 */
    @NotBlank
    private String zzys;
    /** 转出原因 */
    @NotBlank
    private String zcyy;
}
