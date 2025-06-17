package com.shdata.health.gzss.common.vo;

import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
/**
 * 药品目录  VO
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DicYpVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 药品字典编码 */
    private String id;
    /** 药物代码 */
    private String ywdm;
    /** 代码类型 */
    private String dmlx;
    /** 院内医疗机构代码 */
    private String ynYljgdm;
    /** 药物化学名 */
    @NotBlank
    private String ymHxm;
    /** 药物商品名 */
    private String ywSpm;
    /** 大分类 */
    @NotBlank
    @DictFormat(dictType = DictType.DICT, dictKey = "GBYWFL")
    private String dflCd;
    /** 剂型 */
    private String jx;
    /** 规格 */
    private String gg;
    /** 单位 */
    private String dw;
    /** 生产厂家 */
    private String ywsytj;
    /** 拼音 */
    private String pinyin;
}
