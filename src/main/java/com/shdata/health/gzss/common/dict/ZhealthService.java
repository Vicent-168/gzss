package com.shdata.health.gzss.common.dict;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "health-zhealth")
public interface ZhealthService {



    /**
     * 通过行政区域代码查询区域名称
     */
    @GetMapping("feign/query/areaName")
    String queryAreaName(@RequestParam("code") String code);

    /**
     * 使用用户ID列表查询用户名称，Map结构
     */
    @PostMapping("feign/getUserNameForMap")
    Map<String, String> getUserNameForMap(@RequestBody List<String> userIds);

    /**
     * 使用用户ID列表查询机构名称，Map结构
     */
    @PostMapping("feign/getOrganNameForMap")
    Map<String, String> getOrganNameForMap(@RequestBody List<String> codeList);
    /**
     * 区域code 查询区域名称
     */
    @GetMapping("/feign/query/area")
    String findAreaByCode(@RequestParam("areaCode") String areaCode);
    /**
     * 医疗机构id获取医疗机构名称
     */
    @GetMapping("/feign/query/yljg")
    String findYljgByCode(@RequestParam("yljgCode") String yljgCode);
    /**
     * 用户id获取用户名称
     */
    @GetMapping("/feign/query/user")
    String findUserById(@RequestParam("userId") String userId);
}
