package com.shdata.health.gzss.common.vo;

import cn.hutool.extra.pinyin.PinyinUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 家医
 *
 * @author wangjie
 * @date 2024/3/4 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignDoctorVo {
    /**
     * 社区医疗机构代码
     */
    private String sqjgdm;
    /**
     * 姓名
     */
    private String xm;

    /**
     * 工号
     */
    private String gh;

    /**
     * 拼音
     */
    private String pinyin;

    public String getPinyin() {
        if (StringUtils.isNotBlank(xm)){
            return PinyinUtil.getFirstLetter(xm, "");
        }
        return pinyin;
    }
}
