package com.shdata.health.gzss.sys.vo.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 随访_血生化检查项目
 *
 * @author dwt
 * @date 2024-07-15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class JCXMBo {
    //字段信息到字典表中取查询
    //检验项目的type
    private String JYXM;
    //检验项目的code
    private String CODE;
    //不同code对应检验项目的名称
    private String NAME;


}
