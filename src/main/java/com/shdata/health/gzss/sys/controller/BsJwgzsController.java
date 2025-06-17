package com.shdata.health.gzss.sys.controller;

import com.shdata.health.gzss.sys.service.BsJwgzsService;
import com.shdata.health.gzss.sys.vo.BsJwgzsVo;
import com.shdata.health.gzss.sys.vo.JwgzsSearchVo;
import com.shdata.health.gzss.sys.vo.bo.BsJwgzsBo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 病史既往骨折史
 *
 * @author dwt
 * @date 2024-07-19
 */
@RestController
@RequestMapping("/bsJwgzs")
public class BsJwgzsController {
    @Autowired
    private BsJwgzsService bsJwgzsService;


    /**
     * 通用子页面-通过档案ID查询病史既往骨折史
     *
     */
    @GetMapping(value ="/getBsJwgzsDataByDaId")
    public ResponseEntity<List<BsJwgzsVo>> getBsJwgzsDataByDaId(@RequestParam("daId") String daId) {
        List<BsJwgzsVo> bsJwgzsDataByDaId = bsJwgzsService.getBsJwgzsDataByDaId(daId);
        return ResponseEntity.ok(bsJwgzsDataByDaId);
    }

    /**
     * 通过档案ID和疾病问诊id查询病史既往骨折史
     *
     */
    @PostMapping(value ="/getBsJwgzsDataByDaIdandZdrq")
    public ResponseEntity<List<BsJwgzsVo>> getBsJwgzsDataByDaIdandZdrq(@RequestBody JwgzsSearchVo vo) {
        return ResponseEntity.ok(bsJwgzsService.getBsJwgzsDataByDaIdandZdrq(vo));
    }

    /**
     * 通过ID逻辑删除病史既往骨折史
     */
    @GetMapping(value ="/delete")
    public ResponseEntity<String> delete(@RequestParam("id") String id) {
        return ResponseEntity.ok(bsJwgzsService.deleteById(id));
    }

    /**
     * 保存或者更新病史既往骨折史数据
     */
    @PostMapping(value ="/save")
    public ResponseEntity<String> save(@RequestBody @Valid BsJwgzsBo bo) {
        return ResponseEntity.ok(bsJwgzsService.saveOrUpdate(bo));
    }

//    /**
//     * 查询病史既往骨折史分页数据
//     */
//    @PostMapping(value ="/page")
//    public ResponseEntity<PageData<BsJwgzsVo>> page(@RequestBody BsJwgzsSearchVo vo) {
//        return ResponseEntity.ok(bsJwgzsService.findByPage(vo));
//    }

}
