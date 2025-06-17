package com.shdata.health.gzss.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.shdata.health.gzss.common.dict.ZhealthService;

import java.util.List;
import java.util.Map;

public class DataUtil {

    /**
     * 使用用户ID列表查询用户名称，Map结构
     */
    public static Map<String, String> getUserNameForMap(List<String> userIds) {
        return SpringUtil.getBean(ZhealthService.class).getUserNameForMap(userIds);
    }

    /**
     * 使用用户ID列表查询机构名称，Map结构
     */
    public static Map<String, String> getOrganNameForMap(List<String> codeList) {
        return SpringUtil.getBean(ZhealthService.class).getOrganNameForMap(codeList);
    }

    /*public static String queryAreaName(String code) {
        return SpringUtil.getBean(ZhealthService.class).queryAreaName(code);
    }*/

    public static String findAreaByCode(String areaCode) {
        return SpringUtil.getBean(ZhealthService.class).findAreaByCode(areaCode);
    }
    public static String findYljgByCode(String yljgCode) {
        return SpringUtil.getBean(ZhealthService.class).findYljgByCode(yljgCode);
    }

    public static String findUserById(String userId) {
        return SpringUtil.getBean(ZhealthService.class).findUserById(userId);
    }

}
