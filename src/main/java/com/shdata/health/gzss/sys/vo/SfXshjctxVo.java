package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 随访_血生化检查提醒  VO
 *
 * @author dwt
 * @date 2024-07-16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshjctxVo {
    /** 档案编号 */
    private String daId;
    /** 身份证号 */
    private String sfzh;
    /** 姓名 */
    private String xm;
    /** 医保卡号 */
    private String ybkh;
    /** 性别 */
    private String xb;
    /** 联系方式 */
    private String lxfs;
    /** 检查日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date Jcrq;
    /** 责任医生 */
    private String zrys;
    // 新增获取年月日的字符串方法
    public String getJcrqAsString() {
        if (Jcrq != null) {
            // 使用 SimpleDateFormat 来格式化 Date 为 "yyyy-MM-dd"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(Jcrq);
        }
        return null;
    }

}
