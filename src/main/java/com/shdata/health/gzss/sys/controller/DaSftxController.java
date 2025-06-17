package com.shdata.health.gzss.sys.controller;

import com.shdata.health.common.base.PageData;
import com.shdata.health.gzss.sys.service.DaSftxService;
import com.shdata.health.gzss.sys.vo.DaSftxSearchVo;
import com.shdata.health.gzss.sys.vo.resp.DaSftxDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 档案随访提醒
 *
 * @author 丁文韬
 * @date 2024-08-06
 */
@Validated
@RestController
@RequestMapping("daSftx")
public class DaSftxController {

    private final DaSftxService daSftxService;

    public DaSftxController(DaSftxService daSftxService) {
        this.daSftxService = daSftxService;
    }

//    /**
//     * 通过ID查询档案随访提醒VO
//     */
//    @GetMapping("get")
//    public ResponseEntity<DaSftxVo> get(@RequestParam("id") String id) {
//        return ResponseEntity.ok(daSftxService.findById(id, DaSftxVo.class));
//    }
//
//    /**
//     * 通过ID逻辑删除档案随访提醒
//     */
//    @GetMapping("delete")
//    public ResponseEntity<String> delete(@RequestParam("id") String id) {
//        return ResponseEntity.ok(daSftxService.deleteById(id));
//    }
//
//    /**
//     * 保存或者更新档案随访提醒数据
//     */
//    @PostMapping("save")
//    public ResponseEntity<String> save(@RequestBody @Valid DaSftxVo vo) {
//        return ResponseEntity.ok(daSftxService.saveOrUpdate(vo));
//    }

//    /**
//     * 查询档案随访提醒分页数据
//     */
//    @PostMapping("page")
//    public ResponseEntity<PageData<DaSftxVo>> page(@RequestBody DaSftxSearchVo vo) {
//        return ResponseEntity.ok(daSftxService.findByPage(vo));
//    }

    /**
     * 随访提醒——多条件的列表分页查询查询
     */
    @PostMapping("findSftxDataByCriterias")
    public ResponseEntity<PageData<DaSftxDto>> findSftxDataByCriterias(@RequestBody DaSftxSearchVo vo) {
        return ResponseEntity.ok(daSftxService.findSftxDataByCriterias(vo));
    }

}
