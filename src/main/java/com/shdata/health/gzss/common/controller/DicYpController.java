package com.shdata.health.gzss.common.controller;

import com.shdata.health.gzss.common.service.DicYpService;
import com.shdata.health.gzss.common.vo.DicYpSearchVo;
import com.shdata.health.gzss.common.vo.DicYpVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 药品目录
 *
 * @author dwt
 * @date 2024-07-19
 */
@Validated
@RestController
@RequestMapping("/dicYp")
public class DicYpController {
    @Autowired
    private DicYpService dicYpService;


    /**
     * 查询药品目录列表
     */
    @GetMapping(value = "/getList")
    public ResponseEntity<List<DicYpVo>> getList() {
        return ResponseEntity.ok(dicYpService.getList());
    }

    /**
     * 根据药物代码查询药品信息
     */
    @GetMapping(value = "/getYpByYwdm")
    public ResponseEntity<DicYpVo> getYpByYwdm(String ywdm) {
        return ResponseEntity.ok(dicYpService.getYpByYwdm(ywdm));
    }

   /**
    * 通过ID逻辑删除药品目录
    */
   @GetMapping(value = "/delete")
   public ResponseEntity<String> delete(@RequestParam("id") String id) {
       return ResponseEntity.ok(dicYpService.deleteById(id));
   }

    /**
     * 档案登记-添加药物目录
     *
     */
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody @Valid DicYpVo vo) {
        return ResponseEntity.ok(dicYpService.saveOrUpdate(vo));
    }

//    /**
//     * 查询药品目录分页数据
//     */
//    @PostMapping(value = "/page")
//    public ResponseEntity<List<DicYpVo>> page(@RequestBody DicYpSearchVo vo) {
//        return ResponseEntity.ok(dicYpService.findByPage(vo));
//    }

}
