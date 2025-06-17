package com.shdata.health.gzss.sys.vo.bo;

import com.shdata.health.gzss.common.vo.PgJgXqVo;
import com.shdata.health.gzss.sys.vo.PgJgVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 评估结果新增 PgJgBo
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgJgBo {
    /** 档案Id */
    private String daId;
    /** 评估结果对象 */
    private PgJgVo pgJgVo;
    /** 评估血生化指标对象集合 */
    private List<XshBoS> xshBoos;

}
