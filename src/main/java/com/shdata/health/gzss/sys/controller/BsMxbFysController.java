package com.shdata.health.gzss.sys.controller;

import com.shdata.health.gzss.sys.service.BsMxbFysService;
import com.shdata.health.gzss.sys.vo.BsMxbFysVo;
import com.shdata.health.gzss.sys.vo.bo.BsMxbFysBo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 病史_慢性病服药史
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Validated
@RestController
@RequestMapping("/bsMxbFys")
public class BsMxbFysController {
    @Autowired
    private BsMxbFysService bsMxbFysService;


    /**
     * 通过档案ID查询病史_慢性病服药史
     */
    @GetMapping(value = "/get/info")
    public ResponseEntity<List<BsMxbFysVo>> getBsMxbFysDataByDaId(@RequestParam("daId") String daId) {
        return ResponseEntity.ok(bsMxbFysService.getBsMxbFysDataByDaId(daId));
    }



    /**
     * 保存或者更新病史_慢性病服药史数据
     */
    @PostMapping(value ="/save")
    public ResponseEntity<String> save(@RequestBody @Valid BsMxbFysBo bo) {
        return ResponseEntity.ok(bsMxbFysService.saveOrUpdate(bo));
    }



}
