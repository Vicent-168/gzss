package com.shdata.health.gzss.sys.vo.resp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 随访_血生化检查_时间分类  VO
 *
 * @author dwt
 * @date 2024-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshjcByDateVo implements Serializable {

    /**
     * 血生化检查时间
     */
    private String date;
    /**
     * 血生化检查指标
     */
    private List<SfXshjcVo> sfXshjcVoList;
}
