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
 * 检验指标配置表实体 对应表名CDC_M_JYZB_SET
 *
 * @author dwt
 * @date 2024-07-18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_M_JYZB_SET")
public class JyxmSet extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId
    private String id;
    /** 检验项目代码 */
    @TableField("jy_xmdm")
    private String jyXmdm;
    /** 检验项目名称 */
    @TableField("jy_xmmc")
    private String jyXmmc;
    /** 检验种类 */
    @TableField("big_code")
    private String bigCode;
    /** 性别类型 */
    @TableField("sex_type")
    private String sexType;
    /** 开始年龄 */
    @TableField("age_from")
    private long ageFrom;
    /** 截止年龄 */
    @TableField("age_to")
    private long ageTo;
    /** 顺序 */
    @TableField("sort")
    private long sort;
    /** 单位 */
    @TableField("dw")
    private String dw;
    /** 数据类型 */
    @TableField("type")
    private String type;
    /** 表示格式 */
    @TableField("format1")
    private String format1;
    /** 范围 */
    @TableField("round")
    private String round;
    /** 提示表示格式 */
    @TableField("format2")
    private String format2;
    /** 参考值 */
    @TableField("jy_ckz")
    private String jyCkz;
    /** 参考值下限 */
    @TableField("from_value")
    private long fromValue;
    /** 下限符号 */
    @TableField("from_sign")
    private String fromSign;
    /** 参考值上限 */
    @TableField("to_value")
    private long toValue;
    /** 上限符号 */
    @TableField("to_sign")
    private String toSign;
    /** 初始化JS */
    @TableField("init_js")
    private String initJs;
    /** 修改触发JS */
    @TableField("change_js")
    private String changeJs;
    /** 备注 */
    @TableField("bz")
    private String bz;
    /** 创建时间 */
    @TableField("create_date")
    private Date createDate;
    /** 更新时间 */
    @TableField("update_date")
    private Date updateDate;
}
