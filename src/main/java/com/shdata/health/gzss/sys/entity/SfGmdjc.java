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
 * 随访_骨密度检查实体 对应表名CDC_TB_SF_GMDJC
 *
 * @author dwt
 * @date 2024-07-16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_SF_GMDJC")
public class SfGmdjc extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    @TableField("ID")
    private String id;

    /** 档案ID */
    @TableField("DA_ID")
    private String daId;

    /** DXA骨密度检查_检查日期 */
    @TableField("DXA_JCRQ")
    private Date dxaJcrq;

    /** 检查医疗机构 */
    @TableField("JCYLJG")
    private String jcyljg;

    /** 骨密度L1 */
    @TableField("GMD_L1")
    private double gmdL1;

    /** 骨密度L2 */
    @TableField("GMD_L2")
    private double gmdL2;

    /** 骨密度L3 */
    @TableField("GMD_L3")
    private double gmdL3;

    /** 骨密度L4 */
    @TableField("GMD_L4")
    private double gmdL4;

    /** 骨密度L1-L4 */
    @TableField("GMD_L1_L4")
    private double gmdL1L4;

    /** 骨密度全髋 */
    @TableField("GMD_QH")
    private double gmdQh;

    /** 骨密度股骨颈 */
    @TableField("GMD_GGJ")
    private double gmdGgj;

    /** T值L1 */
    @TableField("TZ_L1")
    private double tzL1;

    /** T值L2 */
    @TableField("TZ_L2")
    private double tzL2;

    /** T值L3 */
    @TableField("TZ_L3")
    private double tzL3;

    /** T值L4 */
    @TableField("TZ_L4")
    private double tzL4;

    /** T值L1-L4 */
    @TableField("TZ_L1_L4")
    private double tzL1L4;

    /** T值全髋 */
    @TableField("TZ_QH")
    private double tzQh;

    /** T值股骨颈 */
    @TableField("TZ_GGJ")
    private double tzGgj;

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
