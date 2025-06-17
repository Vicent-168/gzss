package com.shdata.health.gzss.sys.entity;

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
 * 评估_结果实体 对应表名CDC_TB_PG_JG
 *
 * @author dwt
 * @date 2024-07-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_PG_JG")
public class PgJg extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 评估编号 */
    @TableId
    @TableField("PG_ID")
    private String pgId;

    /** 档案ID */
    @TableField("DA_ID")
    private String daId;

    /** 诊断 参照【字典：诊断】 */
    @TableField("ZD")
    private String zd;

    /** 骨密度检查开始 YYYY-MM-DD */
    @TableField("GMDJCKS")
    private Date gmdjcks;

    /** 骨密度检查结束 YYYY-MM-DD */
    @TableField("GMDJCJS")
    private Date gmdjcjs;

    /** 骨密度显示数 */
    @TableField("GMD_XSS")
    private Long gmdXss;

    /** 骨密度开始记录数 */
    @TableField("GMD_KSJLS")
    private Long gmdKsjls;

    /** 检验项目 参照【字典：检验项目】 */
    @TableField("JYXMS")
    private String jyxms;

    /** 血生化检查开始 YYYY-MM-DD */
    @TableField("XSHJCKS")
    private Date xshjcks;

    /** 血生化检查结束 YYYY-MM-DD */
    @TableField("XSHJCJS")
    private Date xshjcjs;

    /** 服药信息开始 YYYY-MM-DD */
    @TableField("FYXXKS")
    private Date fyxxks;

    /** 服药信息结束 YYYY-MM-DD */
    @TableField("FYXXJS")
    private Date fyxxjs;

    /** 评估医生ID */
    @TableField("PGYSID")
    private String pgysid;

    /** 评估医生 */
    @TableField("PGYS")
    private String pgys;

    /** 评估日期 YYYY-MM-DD */
    @TableField("PGRQ")
    private Date pgrq;

    /** 管理机构 */
    @TableField("GLJG")
    private String gljg;

    /** 评估结果 */
    @TableField("PGJG")
    private String pgjg;
}
