package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 随访服药登记信息  VO
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfFyxxdjVo implements Serializable {


    /** 处方日期 */
    /** 处方日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date cfrq;
    /** 处方医疗机构 */
    @NameField(type = DictType.Yljg,target = "cfyljgmc")
    private String cfyljg;
    private String cfyljgmc;
    /** 药物不良反应 */
    @NameField(type = DictType.DICT,key = "YW",target = "ywblfyName")
    private String ywblfy;
    private String ywblfyName;
    /** 随访方式 */
    @NameField(type = DictType.DICT,key = "SFFS",target = "sffsName")
    private String sffs;
    private String sffsName;
    /** 随访医生id */
    //@NameField(type = DictType.User,target = "sfys")
    @DictFormat(dictType = DictType.User)
    private String sfysid;
    //private String sfys;
    /** 管理机构 */
    @NameField(type =DictType.Yljg,target = "gljgmc" )
    private String gljg;
    private String gljgmc;
    //服药信息集合
    private List<SfFyxxVo> sfFyxxVos;
}
