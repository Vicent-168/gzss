package com.shdata.health.gzss.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class QueryLimitVo implements Serializable {

    /**
     * 是否拥有所有权限
     */
    private boolean all;
    /**
     * 拥有权限的区县代码
     */
    private String qu;
    /**
     * 拥有权限的社区机构代码
     */
    private String yljgdm;
    /**
     * 拥有权限的医生工号
     */
    private String gh;
    /**
     * 拥有权限的医生工号
     */
    private List<String> ghList;

    /**
     * 拥有权限的医
     */
    private List<UserInfo> myUsers;

    /**
     * 权限集合
     */
    private List<String> permissions;

    public void addGh(String gh) {
        if (ghList == null) {
            this.ghList = new ArrayList<>();
        }
        this.ghList.add(gh);
    }
}
