package com.shdata.health.gzss.sys.vo.bo;

import com.shdata.health.gzss.sys.vo.BsJwgzsVo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 病史既往骨折史  BsJwgzsBo
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BsJwgzsBo implements Serializable {
    /** 档案ID */
    @NotNull
    private String daId;
    /** 档案ID */
    private List<BsJwgzsVo> bsJwgzsVoList;
}
