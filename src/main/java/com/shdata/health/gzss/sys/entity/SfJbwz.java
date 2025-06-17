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
 * 疾病问诊实体 对应表名CDC_TB_SF_JBWZ
 *
 * @author dwt
 * @date 2024-07-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_SF_JBWZ")
public class SfJbwz extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    @TableField("ID")
    private String id;

    /** 档案ID */
    @TableField("DA_ID")
    private String daId;

    /** 问诊日期 */
    @TableField("WZRQ")
    private Date wzrq;

    /** 问诊医疗机构 */
    @TableField("WZYLJG")
    private String wzyljg;

    /** 症状 */
    @TableField("ZZ")
    private String zz;

    /** 新发症状 */
    @TableField("XFZZ")
    private String xfzz;

    /** 新发骨折 */
    @TableField("XFGZ")
    private String xfgz;

    /** 新发骨折次数 */
    @TableField("XFGZCS")
    private String xfgzcs;

    /** 随访期间发生跌倒 */
    @TableField("SFQJFSDD")
    private String sfqjfsdd;

    /** 跌倒次数 */
    @TableField("DDCS")
    private String ddcs;

    /** 治疗情况依从性 */
    @TableField("ZLQKYCX")
    private String zlqkycx;

    /** 骨质疏松症危险因素 */
    @TableField("GZSSZWXYS")
    private String gzsszwxys;

    /** 身高是否较前1年缩短2cm以上 */
    @TableField("SG_SD")
    private String sgSd;

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
