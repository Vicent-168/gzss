package com.shdata.health.gzss.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shdata.health.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * 病史_饮酒情况实体 对应表名CDC_TB_BS_YJQK
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_BS_YJQK")
public class BsYjqk extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    @TableField("ID")
    private String id;
    /** 档案ID */
    @TableField("DA_ID")
    private String daId;
    /** 饮酒情况  参照【字典：饮酒情况】 */
    @TableField("YJZL")
    private String yjzl;
    /** 饮酒频率  次/周 */
    @TableField("YJPL")
    private BigDecimal yjpl;
    /** 饮酒每次量  ml/次 */
    @TableField("YJMCL")
    private BigDecimal yjmcl;
}
