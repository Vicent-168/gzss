package com.shdata.health.gzss.common.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.common.service.PgJgXqService;
import com.shdata.health.gzss.common.vo.PgJgXqSearchVo;
import com.shdata.health.gzss.common.vo.PgJgXqVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 评估_结果_详情
 *
 * @author 丁文韬
 * @date 2024-07-25
 */
@Validated
@RestController
@RequestMapping("/pgJgXq")
public class PgJgXqController {
    @Autowired
    private PgJgXqService pgJgXqService;

    /**
     * 通过ID查询评估_结果_详情VO
     */
    @GetMapping(value = "/get")
    public ResponseEntity<PgJgXqVo> get(@RequestParam("id") String id) {
        return ResponseEntity.ok(pgJgXqService.findById(id, PgJgXqVo.class));
    }

    /**
     * 通过ID逻辑删除评估_结果_详情
     */
    @GetMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam("id") String id) {
        return ResponseEntity.ok(pgJgXqService.deleteById(id));
    }

    /**
     * 保存或者更新评估_结果_详情数据
     */
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody @Valid PgJgXqVo vo) {
        return ResponseEntity.ok(pgJgXqService.saveOrUpdate(vo));
    }

    /**
     * 查询评估_结果_详情分页数据
     */
    @PostMapping(value = "/page")
    public ResponseEntity<PageData<PgJgXqVo>> page(@RequestBody PgJgXqSearchVo vo) {
        return ResponseEntity.ok(pgJgXqService.findByPage(vo));
    }

}
