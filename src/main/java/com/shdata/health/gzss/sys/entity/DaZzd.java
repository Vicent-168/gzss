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
 * 档案_转诊单实体 对应表名CDC_TB_DA_ZZD
 *
 * @author 丁文韬
 * @date 2024-07-26
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_DA_ZZD")
public class DaZzd extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    @TableField("ID")
    private String id;
    /** 档案ID */
    @TableField("DA_ID")
    private String daId;
    /** 转出医疗机构 */
    @TableField("ZCYLJG")
    private String zcyljg;
    /** 转诊状态 */
    @TableField("ZZZT")
    private String zzzt;
    /** 转诊类别 */
    @TableField("ZZLB")
    private String zzlb;
    /** 转入医疗机构 */
    @TableField("ZRYLJG")
    private String zryljg;
    /** 转诊日期 */
    @TableField("ZZRQ")
    private Date zzrq;
    /** 转诊医生ID */
    @TableField("ZZYSID")
    private String zzysid;
    /** 转诊医生 */
    @TableField("ZZYS")
    private String zzys;
    /** 转出原因 */
    @TableField("ZCYY")
    private String zcyy;
}
