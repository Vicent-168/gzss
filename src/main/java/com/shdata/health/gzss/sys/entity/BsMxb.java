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
 * 病史慢性病实体 对应表名CDC_TB_BS_MXB
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_BS_MXB")
public class BsMxb extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    @TableField("ID")
    private String id;
    /** 档案ID */
    @TableField("DA_ID")
    private String daId;
    /** 慢性疾病 */
    @TableField("MXJB")
    private String mxjb;
    /** 其他慢性疾病 */
    @TableField("QTMXJB")
    private String qtmxjb;
    /** 确诊年月 */
    @TableField("QZNY")
    private Date qzny;
}
