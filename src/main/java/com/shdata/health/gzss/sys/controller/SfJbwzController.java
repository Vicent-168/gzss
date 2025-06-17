package com.shdata.health.gzss.sys.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.sys.service.SfJbwzService;
import com.shdata.health.gzss.sys.vo.SfJbwzSearchVo;
import com.shdata.health.gzss.sys.vo.SfJbwzVo;
import com.shdata.health.gzss.sys.vo.SfyljbwzVo;
import com.shdata.health.gzss.sys.vo.bo.SfJbwzBo;
import com.shdata.health.gzss.sys.vo.resp.SfJbwzDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 疾病问诊
 *
 * @author dwt
 * @date 2024-07-22
 */
@RestController
@RequestMapping("/sfJbwz")
public class SfJbwzController {
    @Autowired
    private SfJbwzService sfJbwzService;


    /**
     * 随访一览-疾病问诊多条件查询
     */
    @PostMapping(value = "/getJbwzDataByCriterias")
    public ResponseEntity<PageData<SfJbwzDto>> getJbwzDataByCriterias(@RequestBody SfJbwzSearchVo vo) {
        return ResponseEntity.ok(sfJbwzService.getJbwzDataByCriteria(vo));
    }


    /**
     * 通过档案ID和问诊日期查询疾病问诊
     */
    @PostMapping(value = "/getJbwzDataByDaIdandDate")
    public ResponseEntity<SfJbwzVo> getJbwzDataByDaIdandDate(@RequestBody SfyljbwzVo vo){
        return ResponseEntity.ok(sfJbwzService.getJbwzDataByDaIdandDate(vo));
    }

    /**
     * 保存或者更新疾病问诊数据
     */
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody @Valid SfJbwzBo bo)  {
        return ResponseEntity.ok(sfJbwzService.saveOrUpdate(bo));
    }


}
