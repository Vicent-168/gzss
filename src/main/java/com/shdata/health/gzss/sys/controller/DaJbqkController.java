package com.shdata.health.gzss.sys.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.sys.entity.DaJbqk;
import com.shdata.health.gzss.sys.service.DaJbqkService;
import com.shdata.health.gzss.sys.service.DaJbqkllService;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.resp.DaJbqkVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 档案管理
 *
 * @author xgb
 * @date 2024-07-10
 */
@RestController
@RequestMapping("/daJbqk")
public class DaJbqkController {
    @Autowired
    private  DaJbqkService daJbqkService;

    @Autowired
    private DaJbqkllService daJbqkllService;

    /**
     * 通过身份证号获取出生日期、性别、年龄
     */
    @GetMapping(value = "/getPsersonalInfo")
    public ResponseEntity<PersonalVo> getPsersonalInfo(@RequestParam("sfzh") String sfzh) {
        return ResponseEntity.ok(daJbqkService.getPsersonalInfo(sfzh));
    }
    /**
     * 根据身高和体重计算BMI
     */
    @PostMapping(value = "/getPsersonalBmiInfo")
    public ResponseEntity<PersonalBmiInfoVo> getPsersonalBmiInfo(@RequestBody PersonalBmiVo vo)  {
        return ResponseEntity.ok(daJbqkService.getPsersonalBmiInfo(vo));
    }


    /**
     * 档案管理-档案一览
     */
    @PostMapping(value = "/getDaDataByCriterias")
    public ResponseEntity<PageData<DaJbqkRespVo>> getDaDataByCriterias(@RequestBody DaJbqkSearchVo vo) {
        return ResponseEntity.ok(daJbqkService.getDaDataByCriterias(vo));
    }

    /**
     * 通过档案ID查询个人简单基本情况
     */
    @GetMapping(value = "/getPsersonalInfoByDaId")
    public ResponseEntity<PersonalInfoVo> getPsersonalInfoByDaId(@RequestParam("daId") String daId) {
        return ResponseEntity.ok(daJbqkService.getPsersonalInfoByDaId(daId));
    }

    /**
     * 通过姓名/身份证号/社保卡号查询个人简单基本情况
     */
    @PostMapping(value = "/getPsersonalInfoByKeyword")
    public ResponseEntity<PersonalInfoVo> getPsersonalInfoByKeyword(@RequestBody PersonalSearchVo vo) {
        return ResponseEntity.ok(daJbqkService.getPsersonalInfoBKeyword(vo));
    }



    /**
     * 通过档案ID查询档案全量信息
     */
    @GetMapping(value = "/getDaJbqkByDaId")
    public ResponseEntity<DaJbqkVo> getDaJbqkByDaId(@RequestParam("daId") String daId) {
        return ResponseEntity.ok(daJbqkService.getDaJbqkByDaId(daId));
    }
    /**
     * 通过身份证号查询档案基本情况
     */
    @GetMapping(value = "/getDaJbqkBySfzh")
    public ResponseEntity<DaJbqkVo> getDaJbqkBySfzh(@RequestParam("sfzh") String sfzh) {
        return ResponseEntity.ok(daJbqkService.getDaJbqkBySfzh(sfzh));
    }
    /**
     * 新建检查前进行档案信息的校验
     */
    @GetMapping(value = "/check")
    public ResponseEntity<Map<String, Object>> checkExistence(@RequestParam("keyword") String keyword,
                                                              @RequestParam(value = "secondKeyword", required = false) String secondKeyword) {
        Map<String, Object> response = new HashMap<>();

        // 第一次输入查询
        List<DaJbqk> results = daJbqkService.findByKeyword(keyword);
        if (results.isEmpty()) {
            response.put("status", "failed");
            response.put("message", "患者信息不存在，请先建档");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else if (results.size() == 1) {
            response.put("status", "success");
            response.put("message", "存在唯一的患者档案信息");
            return ResponseEntity.ok(response);
        } else {
            if (secondKeyword == null || secondKeyword.isEmpty()) {
                response.put("status", "failed");
                response.put("message", "根据姓名查询到多个结果，请输入身份证号或社保卡号进行进一步查询");
                return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(response);
            } else {
                DaJbqk uniqueResult = daJbqkService.findByUniqueKeyword(secondKeyword);
                if (uniqueResult != null) {
                    response.put("status", "success");
                    response.put("message", "存在唯一的患者档案信息");
                    return ResponseEntity.ok(response);
                } else {
                    response.put("status", "failed");
                    response.put("message", "患者信息不存在，请先建档");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            }
        }
    }


    /**
     * 通过daId逻辑删除档案基本情况
     */
    @GetMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam("daId") String daId) {
        return ResponseEntity.ok(daJbqkService.deleteByDaId(daId));
    }

    /**
     * 档案登记_保存或者更新基本信息表
     */
    /* @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody @Valid DaJbqkVo vo) {
        return ResponseEntity.ok(daJbqkService.saveOrUpdate(vo));
    } */


    /**
     * 档案登记-保存更新
     */
    @PostMapping(value = "/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody DaAllVo vo) {
        return ResponseEntity.ok(daJbqkService.saveOrUpdateAll(vo));
    }

}
