package com.shdata.health.gzss.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 机构字典
 */
@Data
public class YljgdmVo implements Serializable {
    /**
     * 22位机构代码
     */
    private String yljgdm;
    /**
     * 机构名称
     */
    private String yljgmc;
    /**
     * 区县代码
     */
    private String qxdm;
    /**
     * 区县名称
     */
    private String qxmc;
    /**
     * 机构等级（区分社区、区属、市属）
     */
    private String yljgdj;
    /**
     * 机构等级名称
     */
    private String yljgdjName;
    /**
     * 机构类别
     */
    private String yljglb;
    /**
     * 机构类别名称
     */
    private String yljglbName;
    /**
     * 机构等次
     */
    private String yljgdc;
    /**
     * 等次名称
     */
    private String yljgdcName;
    /**
     * 本组
     */
    private String bz;
    /**
     * 机构简称
     */
    private String yyjc;
    /**
     * 地址信息
     */
    private String dzxx;
    /**
     * 联系电话
     */
    private String lxdh;

}
