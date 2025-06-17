package com.shdata.health.gzss.sys.vo;

import com.shdata.health.gzss.common.vo.PgJgXqVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 评估结果及评估详情  VO
 *
 * @author dwt
 * @date 2024-07-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgJgDataVo implements Serializable {
    /** 评估结果信息 */
    private PgJgVo pgJgVo;
    /** 评估结果中血生化检验项目详情信息 *//*
    private List<PgJgXqVo> pgJgXqVo; */

}
