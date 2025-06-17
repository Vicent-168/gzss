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
 * 随访_血生化检查实体 对应表名CDC_TB_SF_XSHJC
 *
 * @author dwt
 * @date 2024-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_SF_XSHJC")
public class SfXshjc extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    @TableField("ID")
    private String id;

    /** 档案ID */
    @TableField("DA_ID")
    private String daId;

    /** 检验日期 */
    @TableField("JYRQ")
    private Date jyrq;

    /** 检验医疗机构 */
    @TableField("JYYLJG")
    private String jyyljg;

    /** 检验种类 */
    @TableField("JYZL")
    private String jyzl;

    /** 单位 */
    @TableField("DW")
    private String dw;

    /** 提示 */
    @TableField("TS")
    private String ts;

    /** 检验项目 */
    @TableField("JYXM")
    private String jyxm;

    /** 检验数值 */
    @TableField("JYSZ")
    private String jysz;

    /** 参考值 */
    @TableField("CKZ")
    private String ckz;

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

    /** 数据来源 */
    @TableField("SJLY")
    private String sjly;

}
