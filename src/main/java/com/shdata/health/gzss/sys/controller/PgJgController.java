package com.shdata.health.gzss.sys.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.sys.service.PgJgService;
import com.shdata.health.gzss.sys.vo.PgJgDataVo;
import com.shdata.health.gzss.sys.vo.PgJgSearchVo;
import com.shdata.health.gzss.sys.vo.PgSearchVo;
import com.shdata.health.gzss.sys.vo.bo.PgJgBo;
import com.shdata.health.gzss.sys.vo.resp.PgJgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评估_结果
 *
 * @author dwt
 * @date 2024-07-25
 */
@RestController
@RequestMapping("/pgJg")
public class PgJgController {
    @Autowired
    private PgJgService pgJgService;

    /**
     * 评估一览_多条件模糊查询
     */
    @PostMapping(value = "/getPgJgDataByCriterias")
    public ResponseEntity<PageData<PgJgDto>> getPgJgDataByCriterias(@RequestBody PgJgSearchVo vo) {
        return ResponseEntity.ok(pgJgService.getPgJgDataByCriterias(vo));
    }


    /**
     * 通过档案ID和评估时间查询评估_结果VO
     */
    @PostMapping(value = "/getPgjgDataByDaIdandPgrq")
    public ResponseEntity<PgJgDataVo> getPgjgDataByDaIdandPgrq(@RequestBody PgSearchVo vo) {
        return ResponseEntity.ok(pgJgService.getPgjgDataByDaIdandPgrq(vo));
    }



    /**
     * 保存或者更新评估_结果数据
     */
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody PgJgBo vo) {
        return ResponseEntity.ok(pgJgService.saveOrUpdate(vo));
    }


}
