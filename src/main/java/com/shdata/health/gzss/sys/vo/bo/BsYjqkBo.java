package com.shdata.health.gzss.sys.vo.bo;

import com.shdata.health.gzss.sys.vo.BsYjqkVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 病史_饮酒情况  VO
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BsYjqkBo implements Serializable {
    /** 档案ID */
    private String daId;

    private List<BsYjqkVo> bsYjqkVoList;

}
