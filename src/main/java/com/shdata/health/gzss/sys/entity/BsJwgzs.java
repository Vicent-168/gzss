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
 * 病史既往骨折史实体 对应表名CDC_TB_BS_JWGZS
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_BS_JWGZS")
public class BsJwgzs extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    @TableField("ID")
    private String id;
    /** 档案ID */
    @TableField("DA_ID")
    private String daId;
    /** 时间 */
    @TableField("SJ")
    private Date sj;
    /** 部位 */
    @TableField("BW")
    private String bw;
    /** 原因 */
    @TableField("YY")
    private String yy;
    /** 疾病问诊Id主键
     * 关联疾病问诊表 */
    @TableField("JBWZ_ID")
    private String jbwzId;
}
