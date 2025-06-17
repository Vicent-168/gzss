package com.shdata.health.gzss.sys.controller;

import com.shdata.health.gzss.sys.service.BsMxbService;
import com.shdata.health.gzss.sys.vo.BsMxbVo;
import com.shdata.health.gzss.sys.vo.bo.BsMxbBo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 病史慢性病
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@RestController
@RequestMapping("/bsMxb")
public class BsMxbController {
    @Autowired
    private BsMxbService bsMxbService;
    /**
     * 通过档案ID查询病史慢性病
     */
    @GetMapping(value = "/get/info")
    public ResponseEntity<List<BsMxbVo>> getMxbData(@RequestParam("daId") String daId) {
        return ResponseEntity.ok(bsMxbService.getMxbData(daId));
    }


    /**
     * 保存或者更新病史慢性病数据
     */
    @PostMapping(value ="/save")
    public ResponseEntity<String> save(@RequestBody @Valid BsMxbBo bo) {
        return ResponseEntity.ok(bsMxbService.saveOrUpdate(bo));
    }



}
