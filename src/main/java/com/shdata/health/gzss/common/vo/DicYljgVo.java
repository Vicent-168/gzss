package com.shdata.health.gzss.common.vo;

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
 * 医疗机构  VO
 *
 * @author 丁文韬
 * @date 2024-11-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DicYljgVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** $column.cname */
    private String yljgdm;
    /** $column.cname */
    private String cname;
    /** $column.cname */
    @NotBlank
    private String sname;
    /** $column.cname */
    @NotBlank
    private String csname;
    /** $column.cname */
    @NotBlank
    private String clevel;
    /** $column.cname */
    @NotBlank
    private String lb;
    /** $column.cname */
    @NotBlank
    private String areadm;
    /** $column.cname */
    @NotBlank
    private String dz;
    /** $column.cname */
    @NotBlank
    private String yzbm;
    /** $column.cname */
    @NotBlank
    private String dhhm;
    /** $column.cname */
    @NotNull
    private long xh;
    /** $column.cname */
    @NotBlank
    private String attrbute1;
    /** $column.cname */
    @NotBlank
    private String attrbute2;
    /** $column.cname */
    @NotBlank
    private String attrbute3;
    /** $column.cname */
    @NotBlank
    private String id;
    /** $column.cname 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateDate;
}
