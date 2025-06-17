package com.shdata.health.gzss.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserInfo implements Serializable {

    /**
     * 用户ID
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 工号
     */
    private String jobNo;
    /**
     * 身份证
     */
    private String idcard;
    /**
     * 联系电话
     */
    private String phone;

}
