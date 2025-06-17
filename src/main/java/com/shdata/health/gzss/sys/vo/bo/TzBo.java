package com.shdata.health.gzss.sys.vo.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 随访_血生化检查  新增Bo
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TzBo {
    private String TZ;
   //code值01 02 03 分别代表-1<=T值<=1,-2.5<T值<-1,T值<-2.5不同的T值范围；
    private String CODE;
    //名字
    private String NAME;
}
