package com.shdata.health.gzss.common.vo;

import lombok.Data;

/**
 * @author wangjie
 * @date 2024/3/8 16:22
 */
@Data
public class DicHospitalVo {
    /**
     * 医疗机构代码
     */
    private String yljgdm;

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
}
