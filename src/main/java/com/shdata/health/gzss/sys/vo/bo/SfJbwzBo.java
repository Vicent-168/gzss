package com.shdata.health.gzss.sys.vo.bo;

import com.shdata.health.gzss.sys.vo.BsJwgzsVo;
import com.shdata.health.gzss.sys.vo.SfJbwzVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 疾病问诊信息  SfJbwzBo
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfJbwzBo implements Serializable {
    /** 档案ID */
    private String daId;
    /** 疾病问诊对象 */
    private SfJbwzVo sfJbwzVo;
    /** 既往骨折历史 */
    private List<BsJwgzsVo>  bsJwgzsVoList;
}
