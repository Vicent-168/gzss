package com.shdata.health.gzss.sys.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 随访_血生化检查  VO
 *
 * @author dwt
 * @date 2024-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshjcVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 档案ID
     */
    @NotNull
    private String daId;
    /**
     * 检验日期 日期格式:yyyy-MM-dd
     */
    @NotNull
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
    /**
     * 检验种类
     */
    @NameField(type = DictType.DICT,key = "JYZL",target = "jyzlName")
    private String jyzl;
    private String jyzlName;

    /**
     * 单位
     */

    private String dw;
    /**
     * 提示
     */
    @NameField(type = DictType.DICT,key = "TS",target = "tsName")
    private String ts;
    private String tsName;
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
     * 检验数值
     */

    private String jysz;
    /**
     * 参考值
     */

    private String ckz;
    /**
     * 随访方式
     */
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
     * 数据来源
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "DIC_SJLY")
    private String sjly;
}
