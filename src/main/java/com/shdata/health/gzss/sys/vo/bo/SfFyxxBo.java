package com.shdata.health.gzss.sys.vo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import com.shdata.health.gzss.sys.vo.SfFyDjxxVo;
import com.shdata.health.gzss.sys.vo.SfFyxxVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 随访服药信息  VO
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfFyxxBo implements Serializable {
    /** 档案ID */
    private String daId;

    /** 处方日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date cfrq;
    /** 处方医疗机构 */
    @NameField(type = DictType.Yljg,target = "cfyljgmc")
    private String cfyljg;
    /** 处方医疗机构名称 */
    private String cfyljgmc;
    /** 药物不良反应 */
    @DictFormat(dictType = DictType.DICT,dictKey = "YW")
    private String ywblfy;
    /** 随访方式 */
    @DictFormat(dictType = DictType.DICT,dictKey = "SFFS")
    private String sffs;
    /** 随访医生ID */
    //@NameField(type = DictType.User,target = "sfys")
    @DictFormat(dictType = DictType.User)
    private String sfysid;
    /** 随访医生名称 */
    private String sfys;
    /** 管理机构 */
    @NameField(type = DictType.Yljg,target = "gljgmc")
    private String gljg;

    private String gljgmc;

    private List<SfFyDjxxVo> sfFyxxVoList;
}
