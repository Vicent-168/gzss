package com.shdata.health.gzss.sys.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.sys.service.SfFyxxService;
import com.shdata.health.gzss.sys.vo.*;
import com.shdata.health.gzss.sys.vo.bo.SfFyxxBo;
import com.shdata.health.gzss.sys.vo.bo.SfFyxxBoList;
import com.shdata.health.gzss.sys.vo.resp.PgSffyDto;
import com.shdata.health.gzss.sys.vo.resp.SfFyDto;
import com.shdata.health.gzss.sys.vo.resp.SfFyxxDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 随访服药信息
 *
 * @author dwt
 * @date 2024-07-19
 */
@RestController
@RequestMapping("/sfFyxx")
public class SfFyxxController {
    @Autowired
    private SfFyxxService sfFyxxService;



    /**
     * 共通子页面
     * 查询处方日期分类下的随访服药信息
     */
    @PostMapping(value = "/getSfFysDataByDaId")
    public ResponseEntity<List<SfFyxxDto>> getSfFysDataByDaId(@RequestBody SfFysSearchVo vo) {
        return ResponseEntity.ok(sfFyxxService.getSfFysDataByDaId(vo));
    }

    /**
     * 随访一览-服药信息的多条件查询功能
     */
    @PostMapping(value = "/getSfFysDataByCriterias")
    public ResponseEntity<PageData<SfFyDto>> getSfFysDataByCriterias(@RequestBody SfFyxxSearchVo vo) {
        return ResponseEntity.ok(sfFyxxService.getSfFysDataByCriterias(vo));
    }

    /**
     * 评估结果-新建服药信息子页面的查询功能
     */
    @PostMapping(value = "/getPgJgFysDataByCriterias")
    public ResponseEntity<List<PgSffyDto>> getPgJgFysDataByCriterias(@RequestBody PgjgFyxxSearchVo vo)  {
        return ResponseEntity.ok(sfFyxxService.getPgJgFysDataByCriterias(vo));
    }

    /**
     * 评估结果-服药信息子页面的查询功能
     */
    @PostMapping(value = "/getPgJgFysDataByDaId")
    public ResponseEntity<List<PgSffyDto>> getPgJgFysDataByDaId(@RequestBody PgSearchVo vo)  {
        return ResponseEntity.ok(sfFyxxService.getPgJgFysDataByDaId(vo));
    }
    /**
     * 通过档案ID查询建档前服药信息
     */
    @GetMapping(value = "/fetchHistoryFyxxDataByDaId")
    public ResponseEntity<List<SfFyxxVo>> fetchHistoryFyxxDataByDaId(@RequestParam("daId") String daId)  {
        return ResponseEntity.ok(sfFyxxService.fetchHistoryFyxxDataByDaId(daId));
    }
    /**
     * 通过档案ID查询最新服药信息
     */
    @GetMapping(value = "/fetchLatestFyxxDataByDaId")
    public ResponseEntity<SfFyxxdjVo> fetchLatestFyxxDataByDaId(@RequestParam("daId") String daId)  {
        return ResponseEntity.ok(sfFyxxService.fetchLatestFyxxDataByDaId(daId));
    }
    /**
     * 通过档案ID和开方日期查询服药信息
     */
    @PostMapping(value = "/fetchFyxxDataByDaIdandDate")
    public ResponseEntity<SfFyxxdjVo> fetchFyxxDataByDaIdandDate(@RequestBody SfFyxxylSearchVo vo)  {
        return ResponseEntity.ok(sfFyxxService.fetchFyxxDataByDaIdandDate(vo));
    }
    /**
     * 档案登记_历史服药信息数据的保存或更新
     */
    @PostMapping(value = "/historySaveOrUpdate")
    public ResponseEntity<String> historySaveOrUpdate(@RequestBody @Valid SfFyxxBoList boList) {
        boList.getBoList().forEach(bo -> {
            sfFyxxService.historySaveOrUpdate(bo);
        });
        return ResponseEntity.ok("保存成功");
    }

    /**
     * 保存或者更新随访服药信息数据
     */
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody @Valid SfFyxxBo bo)  {
        return ResponseEntity.ok(sfFyxxService.saveOrUpdate(bo));
    }


}
