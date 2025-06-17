package com.shdata.health.gzss.sys.vo;

import com.shdata.health.gzss.sys.vo.bo.*;
import com.shdata.health.gzss.sys.vo.resp.DaJbqkVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 档案登记页面  DaAllVo
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaAllVo implements Serializable {
    /**
     * 基本信息表对象
     */
    private DaJbqkVo daJbqkVo;
    /**
     * 既往骨折史对象
     */
    private BsJwgzsBo bsJwgzsBo;
    /**
     * 病史慢性病对象
     */
    private BsMxbBo bsMxbBo;
    /**
     * 病史慢性病服药史对象
     */
    private BsMxbFysBo bsMxbFysBo;
    /**
     * 病史饮酒情况对象
     */
    private BsYjqkBo bsYjqkBo;
    /**
     * 骨密度检查对象
     */
    private SfGmdjcVo sfGmdjcVo;
    /**
     * 随访服药信息对象
     */
    private SfFyxxBo sfFyxxBo;
    /**
     * 随访血生化对象
     */
    private SfXshjcRequestVo request;

}
