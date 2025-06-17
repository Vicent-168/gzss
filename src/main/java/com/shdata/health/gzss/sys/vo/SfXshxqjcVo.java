package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import com.shdata.health.gzss.sys.vo.resp.SfXshjcVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshxqjcVo implements Serializable {
    /**
     * 档案id
     */
    private String daId;
    /**
     * 检验日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jyrq;
    /**
     * 检验医疗机构
     */
    @NameField(type =DictType.Yljg,target = "jyyljgmc" )
    private String jyyljg;
    /**
     * 检验医疗机构名称
     */
    private String jyyljgmc;
    /** 随访方式 */
    @NameField(type = DictType.DICT,key = "SFFS",target = "sffsName")
    private String sffs;
    private String sffsName;
    /**
     * 随访医生id
     */
    //@NameField(type = DictType.User,target = "sfys")
    @DictFormat(dictType = DictType.User)
    private String sfysid;
    /**
     * 随访医生
     */
    private String sfys;
    /**
     * 管理机构
     */
    @NameField(type =DictType.Yljg,target = "gljgmc" )
    private String gljg;
    /**
     * 管理机构名称
     */
    private String gljgmc;
    /**
     * 血生化信息
     */
    private List<SfXshjcVo> xshjcVoList;
}
