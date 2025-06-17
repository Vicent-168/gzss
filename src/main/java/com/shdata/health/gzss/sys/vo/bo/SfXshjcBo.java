package com.shdata.health.gzss.sys.vo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import com.shdata.health.gzss.sys.vo.resp.SfXshjcVo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 随访_血生化检查  新增Bo
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshjcBo implements Serializable {
    /**
     * 档案ID
     */
    private String daId;
    /**
     * 检验日期 日期格式:yyyy-MM-dd
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jyrq;
    @NotNull
    /** 检验医疗机构 */
    @DictFormat(dictType = DictType.Yljg)
    private String jyyljg;
    /** 随访方式 */
    @DictFormat(dictType = DictType.DICT, dictKey = "SFFS")
    private String sffs;
    /** 随访医生ID */
    @DictFormat(dictType = DictType.User)
    private String sfysid;
    /** 管理机构 */
    @DictFormat(dictType = DictType.Yljg)
    private String gljg;

    private List<SfXshjcVo> sfXshjcVoList;
}
