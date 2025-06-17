package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
/**
 * 病史_慢性病服药史  VO
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BsMxbFysVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;
    /** 档案ID */
    private String daId;
    /** 慢性疾病药物种类 */
    @NotBlank
    //@NameField(type = DictType.DICT,key = "MXJBYWZL",target = "mxjbywzlName")
    @DictFormat(dictType = DictType.DICT, dictKey = "MXJBYWZL")
    private String mxjbywzl;
    /** 药物名称 */
    @NotBlank
    private String ywmc;
}
