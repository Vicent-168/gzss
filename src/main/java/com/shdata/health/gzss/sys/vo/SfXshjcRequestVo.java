package com.shdata.health.gzss.sys.vo;

import com.shdata.health.gzss.sys.vo.bo.SfXshjcBo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 血生化检查  VO
 *
 * @author dwt
 * @date 2024-07-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshjcRequestVo implements Serializable {
    /** 档案ID */
    @NotBlank
    private String daId;
    /** 不同日期下的血生化检查指标合集 */
    private List<SfXshjcBo> boList;
}
