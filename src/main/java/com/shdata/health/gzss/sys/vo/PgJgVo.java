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
import java.util.List;
/**
 * 评估_结果  VO
 *
 * @author dwt
 * @date 2024-07-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgJgVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 评估编号 */
    private String pgId;
    @NotNull
    /** 档案ID */
    private String daId;
    /** 诊断  参照【字典：诊断】 */
    @NameField(type = DictType.DICT,key = "ZD",target = "zdName")
    private String zd;
    private String zdName;
    /** 骨密度检查开始  YYYY-MM-DD 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmdjcks;
    /** 骨密度检查结束  YYYY-MM-DD 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmdjcjs;
    /** 骨密度显示数 */
    @NotNull
    private Long gmdXss;
    /** 骨密度开始记录数 */
    @NotNull
    private Long gmdKsjls;
    /** 血生化检验项目  参照【字典：检验项目】 */
    private String jyxms;
    /** 血生化检查开始  YYYY-MM-DD 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xshjcks;
    /** 血生化检查结束  YYYY-MM-DD 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xshjcjs;
    /** 服药信息开始  YYYY-MM-DD 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date fyxxks;
    /** 服药信息结束  YYYY-MM-DD 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date fyxxjs;
    /** 评估医生ID */
    @NotBlank
    @DictFormat(dictType = DictType.User)
    private String pgysid;
    /** 评估医生 */
    @NotBlank
    private String pgysidName;
    /** 评估日期  YYYY-MM-DD 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date pgrq;
    /** 管理机构 */
    @DictFormat(dictType = DictType.Yljg)
    private String gljg;
    /** 评估结果 */
    @NotBlank
    private String pgjg;
}
