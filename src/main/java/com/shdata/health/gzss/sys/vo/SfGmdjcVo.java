package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 随访_骨密度检查  VO
 *
 * @author dwt
 * @date 2024-07-16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfGmdjcVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 档案ID
     */
    private String daId;
    /**
     * DXA骨密度检查_检查日期 日期格式:yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dxaJcrq;
    /**
     * 检查医疗机构
     */
    @NameField(type =DictType.Yljg,target = "jcyljgmc" )
    private String jcyljg;
    private String jcyljgmc;
    /**
     * 骨密度L1
     */
    private BigDecimal gmdL1;
    /**
     * 骨密度L2
     */

    private BigDecimal gmdL2;
    /**
     * 骨密度L3
     */

    private BigDecimal gmdL3;
    /**
     * 骨密度L4
     */

    private BigDecimal gmdL4;
    /**
     * 骨密度L1-L4
     */

    private BigDecimal gmdL1L4;
    /**
     * 骨密度全髋
     */

    private BigDecimal gmdQh;
    /**
     * 骨密度股骨颈
     */

    private BigDecimal gmdGgj;
    /**
     * T值L1
     */

    private BigDecimal tzL1;
    /**
     * T值L2
     */

    private BigDecimal tzL2;
    /**
     * T值L3
     */

    private BigDecimal tzL3;
    /**
     * T值L4
     */

    private BigDecimal tzL4;
    /**
     * T值L1-L4
     */

    private BigDecimal tzL1L4;
    /**
     * T值全髋
     */

    private BigDecimal tzQh;
    /**
     * T值股骨颈
     */

    private BigDecimal tzGgj;
    /** 随访方式 */
    @NameField(type = DictType.DICT,key = "SFFS",target = "sffsName")
    private String sffs;
    private String sffsName;

    /** 随访医生id */
    //@NameField(type = DictType.User,target = "sfys")
    @DictFormat(dictType = DictType.User)
    private String sfysid;
    private String sfys;
    /** 管理机构 */
    @NameField(type =DictType.Yljg,target = "gljgmc" )
    private String gljg;
    private String gljgmc;

}
