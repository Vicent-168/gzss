package com.shdata.health.gzss.sys.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.sys.service.PgJgService;
import com.shdata.health.gzss.sys.service.SfXshjcService;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.resp.PgjgXshVo;
import com.shdata.health.gzss.sys.vo.resp.SfXshjcByDateVo;
import com.shdata.health.gzss.sys.vo.resp.SfXshjcByXmDto;
import com.shdata.health.gzss.sys.vo.resp.SfXshjcVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 随访_血生化检查
 *
 * @author dwt
 * @date 2024-07-13
 */
@RestController
@RequestMapping("/sfXshjc")
public class SfXshjcController {
    @Autowired
    private SfXshjcService sfXshjcService;
    @Autowired
    private PgJgService pgJgService;

    /**
     * 血生化excel检查导入
     */
    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(MultipartFile file) {

        return ResponseEntity.ok(sfXshjcService.upload(file));
    }


    /**
     * 通过档案ID查询最新随访_血生化检查VO
     */
    @GetMapping(value = "/fetchLatestBiochemicalCheckData")
    public ResponseEntity<SfXshxqjcVo> fetchLatestBiochemicalCheckData(@RequestParam("daId") String daId) {
        return ResponseEntity.ok(sfXshjcService.fetchLatestBiochemicalCheckData(daId));
    }

    /**
     * 通过档案ID和检查日期查询随访_血生化检查VO
     */
    @PostMapping(value = "/fetchBiochemicalCheckDataBydaIdandCjrq")
    public ResponseEntity<SfXshxqjcVo> fetchBiochemicalCheckDataBydaIdandCjrq(@RequestBody XshYlSearchVo vo) {

        return ResponseEntity.ok(sfXshjcService.fetchBiochemicalCheckDataBydaIdandCjrq(vo));
    }

    /**
     * 通过档案ID查询日期分类下的随访_血生化检查VO
     */
    @GetMapping(value = "/fetchBloodBiochemistryIndicatorsByDaIdAndDateCategory")
    public ResponseEntity<PageData<SfXshjcByDateVo>> fetchBloodBiochemistryIndicatorsByDaIdAndDateCategory(@RequestParam("daid") String daid,
                                                                                                           @RequestParam(value = "page", defaultValue = "1") long page,
                                                                                                           @RequestParam(value = "pageSize", defaultValue = "15") long pageSize) {
        return ResponseEntity.ok(sfXshjcService.fetchBloodBiochemistryIndicatorsByDaIdAndDateCategory(daid, page, pageSize));
    }

    /**
     * 通过档案ID查询检验项目分类下的随访_血生化检查VO
     */
    @GetMapping(value = "/getXshDataByXmAndDaid")
    public ResponseEntity<PageData<SfXshjcByXmDto>> getXshDataByXmAndDaid(@RequestParam("daid") String daid, @RequestParam(value = "page", defaultValue = "1") long page,
                                                                          @RequestParam(value = "pageSize", defaultValue = "10") long pageSize) {
        return ResponseEntity.ok(sfXshjcService.getXshDataByXmAndDaid(daid, page, pageSize));
    }

    /**
     * 评估结果-新建血生化检查查询
     */
    @PostMapping(value = "/getPgXshDataByCriterias")
    public ResponseEntity<PageData<PgjgXshVo>> getPgXshDataByCriterias(@RequestBody PgjgXshSearchVo vo) {
        return ResponseEntity.ok(sfXshjcService.getPgXshDataByCriterias(vo));
    }

    /**
     * 评估结果-详情血生化检查查询
     *//*
    @PostMapping(value = "/getPgXshXqDataByCriterias")
    public ResponseEntity<PageData<PgjgXshVo>> getPgXshXqDataByCriterias(@RequestBody PgjgXshSearchVo vo) {
        return ResponseEntity.ok(sfXshjcService.getPgXshXqDataByCriterias(vo));
    } */

    /**
     * 评估结果-详情血生化检查查询
     */
    @GetMapping(value = "/getPgXshXqDataByPgId")
    public ResponseEntity<XshxqVo> getPgXshXqDataByPgId(@RequestParam("pgId") String pgId) {
        return ResponseEntity.ok(pgJgService.getPgXshXqDataByPgId(pgId));
    }

    //    /**
//     * 随访一览-血生化检查查询
//     */
//    @PostMapping(value = "/getSfXshDataByCriteriasByDaId")
//    public ResponseEntity<PageData<SfXshjcYlvo>> getSfXshDataByCriteriasByDaId(@RequestBody SfXshjcSearchVo vo) {
//        return ResponseEntity.ok(sfXshjcService.getSfXshDataByCriteriasByDaId(vo));
//    }

    /**
     * 随访一览-血生化检查查询
     */
    @PostMapping("/api/xshjc/search")
    public ResponseEntity<PageData<SfXshjcYlvo>> searchPagedResults(@RequestBody SfXshjcSearchVo searchVo) {
        return ResponseEntity.ok(sfXshjcService.searchPagedResults(searchVo));
    }

    /**
     * 随访一览-血生化登记检查查询
     */
    @GetMapping(value = "/getXshDataByDaId")
    public ResponseEntity<List<SfXshjcVo>> getXshDataByDaId(@RequestParam("daid") String daid) {
        return ResponseEntity.ok(sfXshjcService.getXshDataByDaId(daid));
    }


    /**
     * 保存或者更新随访_血生化检查数据
     */
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody @Valid SfXshjcRequestVo request) {
        return ResponseEntity.ok(sfXshjcService.saveOrUpdate(request));
    }


}
