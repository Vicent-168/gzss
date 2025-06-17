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
 * 档案随访提醒实体 对应表名CDC_TB_DA_SFTX
 *
 * @author 丁文韬
 * @date 2024-08-06
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_DA_SFTX")
public class DaSftx extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    private String id;
    /** 档案ID */
    @TableField("da_id")
    private String daId;
    /** 提醒内容 */
    @TableField("txnr")
    private String txnr;
    /** 最新时间 */
    @TableField("zxsj")
    private Date zxsj;
    /** 提醒备注 */
    @TableField("txbz")
    private String txbz;
}
