package com.shdata.health.gzss.sys.vo.resp;

import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 随访_血生化检查_项目分类  VO
 *
 * @author dwt
 * @date 2024-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshjcByXmDto {
    /**
     * 检验项目
     */
    @NameField(type = DictType.DICT,key = "JYXM",target = "jyxmName")
    private String jyxm;
    /**
     * 检验项目名称
     */
    private String jyxmName;
    /**
     * 血生化检查
     */
    private List<SfXshjcVo> sfXshjcVoList;
}
