package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
/**
 * 病史慢性病  VO
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BsMxbVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;
    /** 档案ID */
    private String daId;
    /** 慢性疾病 */
    //@NameField(type = DictType.DICT,key = "MXJB",target = "mxjbName")
    @DictFormat(dictType = DictType.DICT, dictKey = "MXJB")
    private String mxjb;
    /** 慢性疾病名称 */
    //private String mxjbName;
    /** 其他慢性疾病 */
    private String qtmxjb;
    /** 确诊年月 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date qzny;
}
