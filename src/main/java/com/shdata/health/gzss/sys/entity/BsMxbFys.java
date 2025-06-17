package com.shdata.health.gzss.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shdata.health.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * 病史_慢性病服药史实体 对应表名CDC_TB_BS_MXBFYS
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_BS_MXBFYS")
public class BsMxbFys extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId
    @TableField("ID")
    private String id;
    /** 档案ID */
    @TableField("da_id")
    private String daId;
    /** 慢性疾病药物种类 */
    @TableField("mxjbywzl")
    private String mxjbywzl;
    /** 药物名称 */
    @TableField("ywmc")
    private String ywmc;
}
