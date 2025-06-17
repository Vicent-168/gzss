package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
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
 * 疾病问诊  VO
 *
 * @author dwt
 * @date 2024-07-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfJbwzVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    /** 档案ID */
    @NotBlank
    private String daId;
    /** 问诊日期 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date wzrq;
    /** 问诊医疗机构 */
    @NameField(type =DictType.Yljg,target = "wzyljgmc" )
    private String wzyljg;
    /** 问诊医疗机构名称 */
    private String wzyljgmc;
    /** 症状 */
    @NotBlank
    @NameField(type = DictType.DICT,key = "ZZ",target = "zzName")
    private String zz;
    /** 症状名称 */
    private String zzName;
    /** 新发症状 */
    @NameField(type = DictType.DICT,key = "XFZZ",target = "xfzzName")
    private String xfzz;
    private String xfzzName;
    /** 新发骨折 */
    @NameField(type = DictType.DICT,key = "SF",target = "xfgzName")
    private String xfgz;
    private String xfgzName;
    /** 新发骨折次数 */
    private String xfgzcs;
    /** 随访期间发生跌倒 */
    @NameField(type = DictType.DICT,key = "SF",target = "sfqjfsddName")
    private String sfqjfsdd;
    private String sfqjfsddName;
    /** 跌倒次数 */
    private String ddcs;
    /** 治疗情况依从性 */
    @NameField(type = DictType.DICT,key = "LC",target = "zlqkycxName")
    private String zlqkycx;
    private String zlqkycxName;
    /** 骨质疏松症危险因素 */
    @NameField(type = DictType.DICT,key = "GZSSWXYS",target = "gzsszwxysName")
    private String gzsszwxys;
    private String gzsszwxysName;
    /** 身高是否较前1年缩短2cm以上 */
    @NameField(type = DictType.DICT,key = "SF",target = "sgSdName")
    private String sgSd;
    private String sgSdName;
    /** 随访方式 */
    @NotBlank
    @NameField(type = DictType.DICT,key = "SFFS",target = "sffsName")
    private String sffs;
    private String sffsName;
    /** 随访医生ID */
    //@NameField(type =DictType.User,target = "sfys" )
    @DictFormat(dictType = DictType.User)
    private String sfysid;
    /** 随访医生 */
    private String sfys;
    /** 管理机构 */
    @NameField(type =DictType.Yljg,target = "gljgmc" )
    private String gljg;
    /** 管理机构名称 */
    private String gljgmc;
}
