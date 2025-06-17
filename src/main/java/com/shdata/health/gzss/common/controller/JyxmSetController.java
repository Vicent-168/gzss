package com.shdata.health.gzss.common.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.common.service.JyxmSetService;
import com.shdata.health.gzss.common.vo.JyxmSetSearchVo;
import com.shdata.health.gzss.common.vo.JyxmSetVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 检验指标配置表
 *
 * @author dwt
 * @date 2024-07-18
 */
@Validated
@RestController
@RequestMapping("/jyxmSet")
public class JyxmSetController {
    @Autowired
    private JyxmSetService jyxmSetService;
//    /**
//     * 通过ID查询检验指标配置表VO
//     */
//    @GetMapping(value = "/get")
//    public ResponseEntity<JyxmSetVo> get(@RequestParam("id") String id) {
//        return ResponseEntity.ok(jyxmSetService.findById(id, JyxmSetVo.class));
//    }
//
//    /**
//     * 通过ID逻辑删除检验指标配置表
//     */
//    @GetMapping(value = "/delete")
//    public ResponseEntity<String> delete(@RequestParam("id") String id) {
//        return ResponseEntity.ok(jyxmSetService.deleteById(id));
//    }

    /**
     * 保存或者更新检验指标配置表数据
     */
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody @Valid JyxmSetVo vo) {
        return ResponseEntity.ok(jyxmSetService.saveOrUpdate(vo));
    }

    /**
     * 查询检验指标配置表分页数据
     */
    @PostMapping(value = "/page")
    public ResponseEntity<PageData<JyxmSetVo>> page(@RequestBody JyxmSetSearchVo vo) {
        return ResponseEntity.ok(jyxmSetService.findByPage(vo));
    }

    /**
     * 根据检验项目代码、性别类型和年龄查询检验指标配置表
     */
    @GetMapping(value = "/findByCriteria")
    public ResponseEntity<JyxmSetVo> findByCriteria(
            @RequestParam("jyXmdm") String jyXmdm,
            @RequestParam("sexType") String sexType,
            @RequestParam("age") Integer age) {
        JyxmSetVo result = jyxmSetService.findByCriteria(jyXmdm, sexType, age);
        return ResponseEntity.ok(result);
    }

    /**
     * 根据检验种类获取检验项目
     */
    @GetMapping(value = "/getJyxmByJyzl")
    public ResponseEntity<List<JyxmSetVo>> getJyxmByJyzl(
            @RequestParam("bigCode") String bigCode) {
        List<JyxmSetVo> result = jyxmSetService.getJyxmByJyzl(bigCode);
        return ResponseEntity.ok(result);
    }


}
