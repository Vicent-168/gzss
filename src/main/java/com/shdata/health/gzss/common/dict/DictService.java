package com.shdata.health.gzss.common.dict;

import cn.hutool.core.util.StrUtil;
import com.shdata.health.common.dict.Dict;
import com.shdata.health.common.utils.DictUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class DictService implements com.shdata.health.common.dict.DictService {
    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private YljgService yljgService;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;


    /**
     * 通过类型查询字典对象
     *
     * @param type
     * @return
     */
    @Override
    public List<Dict> findByType(String type) {
        return dictMapper.findDictByType(type);
    }

    /**
     * 通过检验项目查询字典对象
     *
     * @return
     */
    public Dict findJyxmByCode(String jyxmCode) {
        return dictMapper.findJyxmByCode(jyxmCode);
    }

    /**
     * 通过字典类型和字典编码查询字典对象
     *
     * @return
     */
    public Dict findByTypeAndCode(String type, String code) {
        return dictMapper.findByTypeAndCode(type, code);
    }

    /**
     * 通过字典类型查询字典对象
     *
     * @return
     */
    public List<Dict> listDictByType(String type) {
        return dictMapper.listDictByType(type);
    }


    public Map<String, List<Dict>> findByTypes(String types) {
        Map<String, List<Dict>> dictMap = new HashMap<>();
        if (StrUtil.isNotBlank(types)) {
            String[] dicTypes = types.split(",");
            for (String type : dicTypes) {
                if (StrUtil.isNotBlank(type)) {
                    type = type.trim();
                    dictMap.put(type, DictUtil.getDicts(type));
                }
            }
        }
        return dictMap;
    }


    /**
     * 通过类型查询字典对象
     *
     * @param type
     * @return
     */
//    public List<Dict> findByType(String type) {
//        return dictMapper.findDictByType(type);
//    }


//    public Map<String, List<Dict>> findByTypes(String types) {
//        Map<String, List<Dict>> dictMap = new HashMap<>();
//        if (StrUtil.isNotBlank(types)) {
//            String[] dicTypes = types.split(",");
//            for (String type : dicTypes) {
//                if (StrUtil.isNotBlank(type)) {
//                    type = type.trim();
//                    dictMap.put(type, DictUtil.getDicts(type));
//                }
//            }
//        }
//        return dictMap;
//    }


}
