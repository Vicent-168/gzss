package com.shdata.health.gzss.common.vo;

import lombok.Data;

/**
 * 医疗机构代码11转22位VO
 */
@Data
public class Yljgdm11To22Vo {

    /**
     * 11位医疗机构代码
     */
    private String yljgdm11;
    /**
     * 22位医疗机构代码
     */
    private String yljgdm22;
    /**
     * 医疗机构名称
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
     * 机构等级
     */
    private String jgdj;
    /**
     * 机构简称
     */
    private String yljgjc;

}
