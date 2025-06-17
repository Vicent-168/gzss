package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
/**
 * 档案_转诊单  VO
 *
 * @author 丁文韬
 * @date 2024-07-26
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaZzdVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;
    /** 档案ID */
    @NotBlank
    private String daId;
    /** 转出医疗机构 */
    @NotBlank
    @NameField(type =DictType.Yljg,target = "zcyljgmc" )
    private String zcyljg;
    /** 转出医疗机构名称 */
    private String zcyljgmc;
    /** 转诊状态 */
    @NotBlank
    /** 转诊状态 */
    @NameField(type = DictType.DICT,key = "ZZZT",target = "zzztName")
    private String zzzt;
    private String zzztName;
    /** 转诊类别 */
    @NotBlank
    /** 转诊类别 */
    @NameField(type = DictType.DICT,key = "ZZLB",target = "zzlbName")
    private String zzlb;
    private String zzlbName;
    /** 转入医疗机构 */
    @NotBlank
    @NameField(type =DictType.Yljg,target = "zryljgmc" )
    private String zryljg;
    /** 转入医疗机构名称 */
    private String zryljgmc;
    /** 转诊日期 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date zzrq;
    /** 转诊医生ID */
    @NotBlank
    @NameField(type =DictType.User,target = "zzys" )
    @DictFormat(dictType = DictType.User)
    private String zzysid;
    /** 转诊医生 */
    private String zzys;
    /** 转出原因 */
    @NotBlank
    private String zcyy;
}
