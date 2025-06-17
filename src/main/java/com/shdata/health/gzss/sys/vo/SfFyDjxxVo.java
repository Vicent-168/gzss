package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 随访服药登记信息  VO
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfFyDjxxVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private String id;
    /** 档案ID */
    private String daId;
    /** 处方日期 */
    private Date cfrq;
    @NameField(type = DictType.Yljg,target = "cfyljgmc")
    private String cfyljg;
    /** 处方医疗机构名称 */
    private String cfyljgmc;
    /** 药物不良反应 */
    @DictFormat(dictType = DictType.DICT,dictKey = "YW")
    private String ywblfy;
    /** 药物分类 */
    @DictFormat(dictType = DictType.DICT,dictKey = "GBYWFL")
    private String ywfl;
    /** 药物代码 */
    private String ywdm;
    /** 药品名称 */
    private String ypmc;
    /** 剂型 */
    private String jx;
    /** 规格 */
    private String gg;
    /** 单位 */
    private String dw;
    /** 随访方式 */
    @DictFormat(dictType = DictType.DICT,dictKey = "SFFS")
    private String sffs;
    /** 随访医生ID */
    //@NameField(type = DictType.User,target = "sfys")
    @DictFormat(dictType = DictType.User)
    private String sfysid;
    /** 随访医生 */
    private String sfys;
    /** 管理机构 */
    @NameField(type = DictType.Yljg,target = "gljgmc")
    private String gljg;
    private String gljgmc;
}
