package com.shdata.health.gzss.sys.vo.bo;

import com.shdata.health.gzss.sys.vo.BsMxbFysVo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 病史_慢性病服药史  BO
 *
 * @author 丁文韬
 * @date 2024-07-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BsMxbFysBo implements Serializable {
    /** 档案ID */
    @NotBlank
    private String daId;

    private List<BsMxbFysVo> bsMxbFysVoList;
}
