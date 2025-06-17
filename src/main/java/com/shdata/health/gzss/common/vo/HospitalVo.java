package com.shdata.health.gzss.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class HospitalVo implements Serializable {

    /**
     * 22位机构代码
     */
    @JsonIgnore
    private String yljgdm22;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;

}
