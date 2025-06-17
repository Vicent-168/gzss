package com.shdata.health.gzss.common.vo;

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
 * 检验指标配置表  VO
 *
 * @author dwt
 * @date 2024-07-18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class JyxmSetVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;
    /** 检验项目代码 */
    @NotBlank
    @DictFormat(dictType = DictType.DICT, dictKey = "JYXM")
    private String jyXmdm;
    /** 检验项目名称 */
    @NotBlank
    private String jyXmmc;
    /** 检验种类 */
    @NotBlank
    @DictFormat(dictType = DictType.DICT, dictKey = "JYZL")
    private String bigCode;
    /** 性别类型 */
    @NotBlank
    @DictFormat(dictType = DictType.DICT, dictKey = "XB")
    private String sexType;
    /** 开始年龄 */
    @NotNull
    private Integer ageFrom;
    /** 截止年龄 */
    @NotNull
    private Integer ageTo;
    /** 顺序 */
    @NotNull
    private long sort;
    /** 单位 */
    @NotBlank
    private String dw;
    /** 数据类型 */
    @NotBlank
    private String type;
    /** 表示格式 */
    @NotBlank
    private String format1;
    /** 范围 */
    @NotBlank
    private String round;
    /** 提示表示格式 */
    @NotBlank
    private String format2;
    /** 参考值 */
    @NotBlank
    @DictFormat(dictType = DictType.DICT, dictKey = "TZ")
    private String jyCkz;
    /** 参考值下限 */
    @NotNull
    private long fromValue;
    /** 下限符号 */
    @NotBlank
    private String fromSign;
    /** 参考值上限 */
    @NotNull
    private long toValue;
    /** 上限符号 */
    @NotBlank
    private String toSign;
    /** 初始化JS */
    @NotBlank
    private String initJs;
    /** 修改触发JS */
    @NotBlank
    private String changeJs;
    /** 备注 */
    @NotBlank
    private String bz;
    /** 创建时间 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;
    /** 更新时间 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateDate;
}
