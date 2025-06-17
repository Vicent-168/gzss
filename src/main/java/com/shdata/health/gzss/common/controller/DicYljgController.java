package com.shdata.health.gzss.common.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.common.service.DicYljgService;
import com.shdata.health.gzss.common.vo.DicYljgSearchVo;
import com.shdata.health.gzss.common.vo.DicYljgVo;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医疗机构接口
 *
 * @author 丁文韬
 * @date 2024-11-25
 */
@Validated
@RestController
@RequestMapping("dicYljg")
public class DicYljgController {

    private final DicYljgService dicYljgService;

    public DicYljgController(DicYljgService dicYljgService) {
        this.dicYljgService = dicYljgService;
    }

    /**
     * 获取医疗机构代码及其名称
     */
    @GetMapping("/getList")
    public ResponseEntity<List<DicYljgVo>> getList( ) {
        return ResponseEntity.ok(dicYljgService.getList( ));
    }

    /**
     * 通过ID查询医疗机构VO
     */
    @GetMapping("get")
    public ResponseEntity<DicYljgVo> get(@RequestParam("id") String id) {
        return ResponseEntity.ok(dicYljgService.findById(id, DicYljgVo.class));
    }

    /**
     * 通过ID逻辑删除医疗机构
     */
    @GetMapping("delete")
    public ResponseEntity<String> delete(@RequestParam("id") String id) {
        return ResponseEntity.ok(dicYljgService.deleteById(id));
    }

    /**
     * 保存或者更新医疗机构数据
     */
    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody @Valid DicYljgVo vo) {
        return ResponseEntity.ok(dicYljgService.saveOrUpdate(vo));
    }

    /**
     * 查询医疗机构分页数据
     */
    @PostMapping("page")
    public ResponseEntity<PageData<DicYljgVo>> page(@RequestBody DicYljgSearchVo vo) {
        return ResponseEntity.ok(dicYljgService.findByPage(vo));
    }

}
