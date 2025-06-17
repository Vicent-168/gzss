package com.shdata.health.gzss.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shdata.health.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * 药品目录实体 对应表名CDC_M_DIC_YP
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_M_DIC_YP")
public class DicYp extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 药品字典编码 */
    @TableId
    private String id;
    /** 药物代码 */
    @TableField("YWDM")
    private String ywdm;
    /** 代码类型 */
    @TableField("DMLX")
    private String dmlx;
    /** 院内医疗机构代码 */
    @TableField("YN_YLJGDM")
    private String ynYljgdm;
    /** 药物化学名 */
    @TableField("YM_HXM")
    private String ymHxm;
    /** 药物商品名 */
    @TableField("YW_SPM")
    private String ywSpm;
    /** 大分类 */
    @TableField("DFL_CD")
    private String dflCd;
    /** 剂型 */
    @TableField("JX")
    private String jx;
    /** 规格 */
    @TableField("GG")
    private String gg;
    /** 单位 */
    @TableField("DW")
    private String dw;
    /** 生产厂家 */
    @TableField("YWSYTJ")
    private String ywsytj;
    /** 拼音 */
    @TableField("PINYIN")
    private String pinyin;
}
