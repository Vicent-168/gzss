package com.shdata.health.gzss.sys.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.sys.service.DaZzdService;
import com.shdata.health.gzss.sys.vo.DaZzdSearchVo;
import com.shdata.health.gzss.sys.vo.DaZzdVo;
import com.shdata.health.gzss.sys.vo.ZzdSearchVo;
import com.shdata.health.gzss.sys.vo.resp.DaZzdDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 档案_转诊单
 *
 * @author 丁文韬
 * @date 2024-07-26
 */
@RestController
@RequestMapping("/daZzd")
public class DaZzdController {
    @Autowired
    private DaZzdService daZzdService;


    /**
     * 转诊一览-多条件模糊查询
     */
    @PostMapping(value = "/getZzdDataByCriterias")
    public ResponseEntity<PageData<DaZzdDto>> getZzdDataByCriterias(@RequestBody DaZzdSearchVo vo) {
        return ResponseEntity.ok(daZzdService.getZzdDataByCriterias(vo));
    }

    /**
     * 通过ID和转诊日期转诊信息
     */
    @PostMapping(value = "/getZzdByDaIdandZzrq")
    public ResponseEntity<DaZzdVo> getZzdByDaIdandZzrq(@RequestBody ZzdSearchVo vo) {
        return ResponseEntity.ok(daZzdService.getZzdByDaIdandZzrq(vo));
    }


    /**
     * 保存或者更新档案_转诊单数据
     */
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody @Valid DaZzdVo vo) {
        return ResponseEntity.ok(daZzdService.saveOrUpdate(vo));
    }


}
