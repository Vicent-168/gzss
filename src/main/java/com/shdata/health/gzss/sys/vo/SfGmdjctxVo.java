package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 随访_骨密度检查提醒  VO
 *
 * @author dwt
 * @date 2024-07-16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfGmdjctxVo implements Serializable {
    /** 档案编号 */
    private String daId;
    /** 身份证号 */
    private String sfzh;
    /** 姓名 */
    private String xm;
    /** 性别 */
    private String xb;
    /** 联系方式 */
    private String lxfs;
    /** 检查日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jcrq;
    /** 责任医生 */
    @DictFormat(dictType = DictType.User)
    private String zrys;
    // 新增获取年月日的字符串方法
    public String getJcrqAsString() {
        if (jcrq != null) {
            // 使用 SimpleDateFormat 来格式化 Date 为 "yyyy-MM-dd"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(jcrq);
        }
        return null;
    }

}
