package com.shdata.health.gzss.sys.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 转诊单信息  DaZzdDto
 *
 * @author dwt
 * @date 2024-07-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaZzdDto {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;
    /** 档案ID */
    private String daId;
    /** 姓名 */
    private String xm;
    /** 身份证号 */
    private String sfzh;
    /** 性别 */
    @NameField(type = DictType.DICT,key = "XB",target = "xbName")
    private String xb;
    private String xbName;
    /** 年龄 */
    private Integer nl;
    /** 联系方式 */
    private String lxfs;
    /** 转诊状态 */
    @NameField(type = DictType.DICT,key = "ZZZT",target = "zzztName")
    private String zzzt;
    private String zzztName;
    /** 转诊类别 */
    @NameField(type = DictType.DICT,key = "ZZLB",target = "zzlbName")
    private String zzlb;
    private String zzlbName;
    /** 转入医疗机构 */
    @NameField(type =DictType.Yljg,target = "zryljgmc" )
    private String zryljg;
    /** 转入医疗机构名称 */
    private String zryljgmc;
    /** 转诊日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date zzrq;
    /** 转出原因 */
    private String zcyy;
    //根据身份证号自动给出生日期和年龄字段赋值
    public void setCsrqAndNlFromIdCard() throws ParseException {
        if (this.sfzh == null || this.sfzh.length() != 18) {
            throw new IllegalArgumentException("身份证号格式不正确");
        }

        // 从身份证号中提取出生日期
        String birthDateStr = this.sfzh.substring(6, 14);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date birthDate = dateFormat.parse(birthDateStr);

        // 计算年龄
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);
        int birthYear = birthCalendar.get(Calendar.YEAR);
        int birthMonth = birthCalendar.get(Calendar.MONTH);
        int birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH);

        Calendar nowCalendar = Calendar.getInstance();
        int currentYear = nowCalendar.get(Calendar.YEAR);
        int currentMonth = nowCalendar.get(Calendar.MONTH);
        int currentDay = nowCalendar.get(Calendar.DAY_OF_MONTH);

        Integer age = currentYear - birthYear;
        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
            age--;
        }

        // 赋值给字段
        this.nl = age;
        // 提取性别信息
        char genderCode = this.sfzh.charAt(16);
        this.xb= (genderCode % 2 == 0) ? "2" : "1";
    }
}
