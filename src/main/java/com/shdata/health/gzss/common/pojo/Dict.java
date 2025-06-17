package com.shdata.health.gzss.common.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shdata.health.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 通用字典 对应表名 CDC_M_DIC_GENERAL
 *
 * @author dwt
 * @date 2024-07-12
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_M_DIC_GENERAL")
public class Dict extends BaseEntity {
    private String type;
    private String code;
    private String name;
    private String sname;
    private int sort;
    private int classType;
    private String fcode;
    private String filter;
    private String ckz;
    private String back2;
    private String back3;
}
