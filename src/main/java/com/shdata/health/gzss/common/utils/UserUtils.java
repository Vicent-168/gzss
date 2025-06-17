package com.shdata.health.gzss.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.shdata.health.common.utils.AuthUtil;
import com.shdata.health.common.utils.RedisUtils;
import com.shdata.health.gzss.common.dict.ZhealthService;

/**
 * @author wangjie
 */
public class UserUtils extends AuthUtil {

    /**
     * 获取指定用户对象
     */
    /*public static User getUser(String userId) {
        String key = StrUtil.format("{}:user", userId);
        User user = RedisUtils.get(key);
        if (user != null) {
            return user;
        }
        user = getZacianService().findUser(SystemConfig.systemId(), userId);
        RedisUtils.set(key, user);
        return user;
    }*/

    /**
     * 通过区域代码获取区域名称
     */
    public static String getAreaName(String areaCode) {
        String key = StrUtil.format("area:{}", areaCode);
        String areaName = RedisUtils.get(key);
        if (StrUtil.isNotBlank(areaName)) {
            return areaName;
        }
        areaName = SpringUtil.getBean(ZhealthService.class).queryAreaName(areaCode);
        if (StrUtil.isNotBlank(areaName)) {
            RedisUtils.set(key, areaName);
        }
        return areaName;
    }


}
