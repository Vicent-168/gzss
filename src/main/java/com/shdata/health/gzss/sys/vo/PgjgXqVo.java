package com.shdata.health.gzss.sys.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgjgXqVo implements Serializable {
    /** 详情id */
    private String xqId;
    /** 评估编号 */
    private String pgId;
    /** 档案id */
    private String daId;
    /** 检验项目 */
    private String jyxm;
    /** 显示数 */
    private Long xxs;
    /** 开始记录数 */
    private Long ksjls;
//    /** 开始时间 */
//    private Date xshjcks;
//    /** 结束时间 */
//    private Date xshjcjs;
}
