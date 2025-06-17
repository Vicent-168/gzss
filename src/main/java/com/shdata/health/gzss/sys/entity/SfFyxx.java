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
 * 随访服药信息实体 对应表名CDC_TB_SF_FYXX
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_SF_FYXX")
public class SfFyxx extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    @TableField("ID")
    private String id;

    /** 档案ID */
    @TableField("DA_ID")
    private String daId;

    /** 处方日期 */
    @TableField("CFRQ")
    private Date cfrq;

    /** 处方医疗机构 */
    @TableField("CFYLJG")
    private String cfyljg;

    /** 药物不良反应 */
    @TableField("YWBLFY")
    private String ywblfy;

    /** 药物分类 */
    @TableField("YWFL")
    private String ywfl;

    /** 药物代码 */
    @TableField("YWDM")
    private String ywdm;

    /** 药品名称 */
    @TableField("YPMC")
    private String ypmc;

    /** 剂型 */
    @TableField("JX")
    private String jx;

    /** 规格 */
    @TableField("GG")
    private String gg;

    /** 单位 */
    @TableField("DW")
    private String dw;

    /** 随访方式 */
    @TableField("SFFS")
    private String sffs;

    /** 随访医生ID */
    @TableField("SFYSID")
    private String sfysid;

    /** 随访医生 */
    @TableField("SFYS")
    private String sfys;

    /** 管理机构 */
    @TableField("GLJG")
    private String gljg;
}
