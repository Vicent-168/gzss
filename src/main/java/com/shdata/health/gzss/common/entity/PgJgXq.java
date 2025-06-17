package com.shdata.health.gzss.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shdata.health.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 评估_结果_详情实体 对应表名CDC_TB_PG_JG_XQ
 *
 * @author 丁文韬
 * @date 2024-07-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_PG_JG_XQ")
public class PgJgXq extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 详情ID */
    @TableId
    private String xqId;
    /** 评估编号 */
    @TableField("pg_id")
    private String pgId;
    /** 档案ID */
    @TableField("da_id")
    private String daId;
    /** 检验项目  参照【字典：检验项目】 */
    @TableField("jyxm")
    private String jyxm;
    /** 显示数 */
    @TableField("xss")
    private long xss;
    /** 开始记录数 */
    @TableField("ksjls")
    private long ksjls;
    /**检验日期 YYYY-MM-DD*/
    @TableField("jyrq")
    private Date jyrq;
    /**评估日期 YYYY-MM-DD*/
    @TableField("pgrq")
    private Date pgrq;
    /** 检验数值 */
    @TableField("jysz")
    private BigDecimal jysz;
}
