package com.shdata.health.gzss.sys.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.sys.service.PgJgService;
import com.shdata.health.gzss.sys.service.SfGmdjcService;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.resp.PgjgGmdDto;
import com.shdata.health.gzss.sys.vo.resp.PgjgXqGmdDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 随访_骨密度检查
 *
 * @author dwt
 * @date 2024-07-16
 */
@RestController
@RequestMapping("/sfGmdjc")
public class SfGmdjcController {
    @Autowired
    private SfGmdjcService sfGmdjcService;

    @Autowired
    private PgJgService pgJgService;

    /**
     * 通过档案ID查询随访最新骨密度信息
     */
    @GetMapping(value = "/fetchLatestGmdCheckData")
    public ResponseEntity<SfGmdjcVo> fetchLatestGmdCheckData(@RequestParam("daId") String daId) {
        return ResponseEntity.ok(sfGmdjcService.fetchLatestGmdCheckData(daId));
    }

    /**
     * 通过档案ID和诊断日期查询随访骨密度信息
     */
    @PostMapping(value = "/fetchGmdCheckDataBydaIdandZdrq")
    public ResponseEntity<SfGmdjcVo> fetchGmdCheckDataBydaIdandZdrq(@RequestBody GmdlySearchvo vo) {
        return ResponseEntity.ok(sfGmdjcService.fetchGmdCheckDataBydaIdandZdrq(vo));
    }


    /**
     * 评估结果-新建页面骨密度检查查询
     */
    @PostMapping(value = "/getPgjgGmdDataByCriterias")
    public ResponseEntity<PageData<PgjgGmdDto>> getPgjgGmdDataByCriterias(@RequestBody @Valid PgjgGmdSearchVo vo) {

        return ResponseEntity.ok(sfGmdjcService.getPgjgGmdDataByCriterias(vo));
    }
    /**
     * 评估结果-详情页面骨密度检查查询
     */
    @PostMapping(value = "/getPgjgXqGmdDataByDaId")
    public ResponseEntity<PageData<PgjgXqGmdDto>> getPgjgXqGmdDataByDaId(@RequestBody PgSearchVo pgSearchVo) {
        return ResponseEntity.ok(sfGmdjcService.getPgjgXqGmdDataByDaId(pgSearchVo));
    }

    /**
     * 随访一览——保存或者更新随访_骨密度检查数据
     *
     */
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody @Valid SfGmdjcVo vo) {
        return ResponseEntity.ok(sfGmdjcService.saveOrUpdate(vo));
    }

    /**
     * 随访一览_骨密度检查
     */
    @PostMapping(value = "/page")
    public ResponseEntity<PageData<SfGmdJcYlo>> page(@RequestBody SfGmdjcSearchVo vo) {
        return ResponseEntity.ok(sfGmdjcService.findByPage(vo));
    }

    /**
     * 共同子页面根据daId和分页参数查询骨密度检查历史数据
     *
     */
    @PostMapping(value = "/getListDataByDaid")
    public ResponseEntity<PageData<SfGmdjcVo>> getListDataByDaid(@RequestBody SfGmdjcSearchVo vo) {
        return ResponseEntity.ok(sfGmdjcService.getListDataByDaid(vo));
    }

}
