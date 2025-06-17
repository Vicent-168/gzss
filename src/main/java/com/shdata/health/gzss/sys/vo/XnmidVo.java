package com.shdata.health.gzss.sys.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
 * 血清骨钙素N端中分子片段
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class XnmidVo implements Serializable {
    /** 检验数值 */
    private String jysz;
    /** 单位 */
    private String dw;
    /** 提示 */
    private String ts;
    public String getjysz(){
        // 使用 StringBuilder 进行字符串拼接
        StringBuilder sb = new StringBuilder();
        // 只有当 jysz 不为空或者不为单个空格时才进行拼接
        if (jysz != null && !jysz.trim().isEmpty()) {
            sb.append(jysz);
        }
        sb.append(dw);
        return sb.toString();
    }


}
