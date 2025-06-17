package com.shdata.health.gzss.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignYLjgVo implements Serializable {

    /**
     * 区县代码
     */
    private String qxdm;
    /**
     * 区县名称
     */
    private String qxmc;
    /**
     * 医疗机构代码
     */
    private String yljgdm;
    /**
     * 医疗机构名称
     */
    private String yljgmc;

}
