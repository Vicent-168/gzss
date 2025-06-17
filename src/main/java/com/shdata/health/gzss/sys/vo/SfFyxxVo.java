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
 * 随访服药信息  VO
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfFyxxVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;
    @NotNull
    /** 档案ID */
    private String daId;
    /** 处方日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date cfrq;
    /** 处方医疗机构 */
    @NameField(type =DictType.Yljg,target = "cfyljgmc" )
    private String cfyljg;
    /** 处方医疗机构名称 */
    private String cfyljgmc;
    /** 药物不良反应 */
    @NameField(type = DictType.DICT,key = "YW",target = "ywblfyName")
    private String ywblfy;
    private String ywblfyName;
    /** 药物分类 */
    @NameField(type = DictType.DICT,key = "GBYWFL",target = "ywflName")
    private String ywfl;
    /** 药物分类名称*/
    private String ywflName;
    /** 药物代码 */
    private String ywdm;
    /** 药品名称 */
    private String ypmc;
    /** 剂型 */
    private String jx;
    /** 规格 */
    private String gg;
    /** 单位 */
    private String dw;
    /** 随访方式 */
    @NameField(type = DictType.DICT,key = "SFFS",target = "sffsName")
    private String sffs;
    /** 随访方式名称*/
    private String sffsName;
    /** 随访医生ID */
    //@NameField(type =DictType.User,target = "sfys" )
    @DictFormat(dictType = DictType.User)
    private String sfysid;
    /** 随访医生 */
    //private String sfys;
    /** 管理机构 */
    @NameField(type =DictType.Yljg,target = "gljgmc" )
    private String gljg;
    private String gljgmc;
}
