
package com.shdata.health.gzss.common.dict;

import com.shdata.health.common.dict.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 区域字典控制器
 */


@RestController
@RequestMapping("/area")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

/**
     * 通过地区编码获取中文名称
     *
     * @param areaCode 地区编码
     * @return 中文名称
     *//*
*/


    @GetMapping("/findByCode")
    public ResponseEntity<String> findByCode(String areaCode) {
        return ResponseEntity.ok(areaService.getName(areaCode));
    }


/**
     * 获取完整的地区地址
     *
     * @param she 省
     * @param shi 市
     * @param xia 县
     * @param xng 乡
     * @param vlg 村
     * @param xxdz 详细地址
     * @return 完整地址
     *//*
*/


    @GetMapping("/getFullAddress")
    public ResponseEntity<String> getFullAddress(String she, String shi, String xia, String xng, String vlg, String xxdz) {
        return ResponseEntity.ok(areaService.getFullAddress(she, shi, xia, xng, vlg, xxdz));
    }

}


