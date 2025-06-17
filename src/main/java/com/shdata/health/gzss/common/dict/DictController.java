package com.shdata.health.gzss.common.dict;

import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.dict.YljgService;
import com.shdata.health.gzss.common.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    private final DictService dictService;

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private YljgService yljgService;

    /**
     * 通用type查询字典合集
     * <p>
     * 根据字典类型查询字典
     * <p>
     */
    @GetMapping("/findByType")
    public ResponseEntity<List<Dict>> findByType(String type) {
        return ResponseEntity.ok(dictService.findByType(type));
    }

    // 新增Area查询接口
    @GetMapping("/areaByCode")
    public ResponseEntity<Dict> findAreaByCode(String areaCode) {
        String areaName = areaService.getName(areaCode);
        Dict dict = new Dict();
        dict.setName(areaName);
        dict.setCode(areaCode);
        return ResponseEntity.ok(dict);
    }

    // 新增Yljg查询接口
    @GetMapping("/yljgByCode")
    public ResponseEntity<Dict> findYljgByCode(String yljgCode) {
        String yljgName = DataUtil.findYljgByCode(yljgCode);
        Dict dict = new Dict();
        dict.setName(yljgName);
        dict.setCode(yljgCode);
        return ResponseEntity.ok(dict);
    }

    // 新增User查询接口
    @GetMapping("/userById")
    public ResponseEntity<Dict> findUserById(String userId) {
        String userName = DataUtil.findUserById(userId);
        Dict dict = new Dict();
        dict.setName(userName);
        dict.setCode(userId);
        return ResponseEntity.ok(dict);
    }

    /**
     * 通用字典查询
     * <p>
     * 若同时查询多个字典，需用逗号隔开。
     * <p>
     */
    @GetMapping("types")
    public ResponseEntity<Map<String, List<Dict>>> type(String types) {
        return ResponseEntity.ok(dictService.findByTypes(types));
    }

    /**
     * 通过type和code查询中文名
     * <p>
     * 若同时查询多个字典，需用逗号隔开。
     * <p>
     */
    @GetMapping("typeAndCode")
    public ResponseEntity<Dict> type(String type, String code) {
        return ResponseEntity.ok(dictService.findByTypeAndCode(type, code));
    }


}
