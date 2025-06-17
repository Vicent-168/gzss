package com.shdata.health.gzss.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.math.BigDecimal;
import java.util.Date;

/**
 * 评估_结果_详情  VO
 *
 * @author 丁文韬
 * @date 2024-07-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgJgXqVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 详情ID */
    private String xqId;
    /** 评估编号 */
    private String pgId;
    /** 档案ID */
    @NotBlank
    private String daId;
    /** 检验日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jyrq;
    /** 评估日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date pgrq;
    /** 检验项目  参照【字典：检验项目】 */
    @NotBlank
    @DictFormat(dictType = DictType.DICT,dictKey = "JYXM")
    private String jyxm;
    /** 检验数值 */
    private BigDecimal jysz;
    /** 显示数 */
    @NotNull
    private Long xss;
    /** 开始记录数 */
    @NotNull
    private Long ksjls;
}
