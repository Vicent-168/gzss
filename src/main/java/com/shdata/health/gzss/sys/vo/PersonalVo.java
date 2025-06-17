package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 评估_结果  搜索VO
 *
 * @author dwt
 * @date 2024-07-23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PersonalVo {
    /** 出生日期 */
    private Date csrq;
    /** 性别 */
    //@NameField(type = DictType.DICT,key = "XB",target = "xbName")
    @DictFormat(dictType = DictType.DICT, dictKey = "XB")
    private String xb;
    //private String xbName;
    /** 年龄 */
    private Integer nl;

}
