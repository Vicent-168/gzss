package com.shdata.health.gzss.sys.controller;

import com.shdata.health.gzss.sys.service.BsYjqkService;
import com.shdata.health.gzss.sys.vo.BsYjqkVo;
import com.shdata.health.gzss.sys.vo.bo.BsYjqkBo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 病史_饮酒情况
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Validated
@RestController
@RequestMapping("/bsYjqk")
public class BsYjqkController {
    @Autowired
    private  BsYjqkService bsYjqkService;
    /**
     * 通过档案ID查询病史_饮酒情况
     */
    @GetMapping(value = "/get/info")
    public ResponseEntity<List<BsYjqkVo>> getYjqkDataByDaId(@RequestParam("daId") String daId) {
        return ResponseEntity.ok(bsYjqkService.getYjqkDataByDaId(daId));
    }


    /**
     * 保存或者更新病史_饮酒情况数据
     */
    @PostMapping(value ="/save")
    public ResponseEntity<String> save(@RequestBody @Valid BsYjqkBo bo) {
        return ResponseEntity.ok(bsYjqkService.saveOrUpdate(bo));
    }


}
