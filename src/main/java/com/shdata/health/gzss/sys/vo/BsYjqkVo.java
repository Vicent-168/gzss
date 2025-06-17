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
import java.math.BigDecimal;

/**
 * 病史_饮酒情况  VO
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BsYjqkVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;
    /** 档案ID */
    private String daId;
    /** 饮酒情况  参照【字典：饮酒情况】 */
    @NotBlank
    //@NameField(type = DictType.DICT,key = "YJQK",target = "yjqkName")
    @DictFormat(dictType = DictType.DICT, dictKey = "YJQK")
    private String yjzl;
//    /** 饮酒情况名称 */
//    private String yjqkName;
    /** 饮酒频率  次/周 */
    @NotNull
    private BigDecimal yjpl;
    /** 饮酒每次量  ml/次 */
    @NotNull
    private BigDecimal yjmcl;
}
