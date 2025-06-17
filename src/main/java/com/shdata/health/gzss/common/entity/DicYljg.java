package com.shdata.health.gzss.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shdata.health.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.Date;

/**
 * 医疗机构实体 对应表名C_DIC_YLJG
 *
 * @author 丁文韬
 * @date 2024-11-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("C_DIC_YLJG")
public class DicYljg extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** $column.cname */
    @TableId
    private String yljgdm;
    /** $column.cname */
    @TableField("cname")
    private String cname;
    /** $column.cname */
    @TableField("sname")
    private String sname;
    /** $column.cname */
    @TableField("csname")
    private String csname;
    /** $column.cname */
    @TableField("clevel")
    private String clevel;
    /** $column.cname */
    @TableField("lb")
    private String lb;
    /** $column.cname */
    @TableField("areadm")
    private String areadm;
    /** $column.cname */
    @TableField("dz")
    private String dz;
    /** $column.cname */
    @TableField("yzbm")
    private String yzbm;
    /** $column.cname */
    @TableField("dhhm")
    private String dhhm;
    /** $column.cname */
    @TableField("xh")
    private long xh;
    /** $column.cname */
    @TableField("attrbute1")
    private String attrbute1;
    /** $column.cname */
    @TableField("attrbute2")
    private String attrbute2;
    /** $column.cname */
    @TableField("attrbute3")
    private String attrbute3;
    /** $column.cname */
    @TableField("id")
    private String id;
    /** $column.cname */
    @TableField("update_date")
    private Date updateDate;
}
