package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * 档案一览  返回VO
 *
 * @author dwt
 * @date 2024-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaJbqkRespVo implements Serializable {

    /**
     * 档案编号
     */
    private String daId;
    /**
     * 姓名
     */
    private String xm;
    /**
     * 性别
     */
    @NameField(type = DictType.DICT, key = "XB", target = "xbName")
    private String xb;
    private String xbName;
    /**
     * 年龄
     */
    private Integer nl;
    /**
     * 身份证号
     */
    private String sfzh;
    /**
     * 联系方式
     */
    private String lxfs;
    /**
     * 居住地址_省
     */
    @NameField(type = DictType.Area, key = "SHENG", target = "jzdzShengName")
    private String jzdzSheng;
    private String jzdzShengName;
    /**
     * 居住地址_市
     */
    @NameField(type = DictType.Area, key = "SHI", target = "jzdzShiName")
    private String jzdzShi;
    private String jzdzShiName;
    /**
     * 居住地址_区县
     */
    @NameField(type = DictType.Area, key = "QX", target = "jzdzQxName")
    private String jzdzQx;
    private String jzdzQxName;
    /**
     * 居住地址_街道
     */
    @NameField(type = DictType.Area, key = "JD", target = "jzdzJdName")
    private String jzdzJd;
    private String jzdzJdName;
    /**
     * 居住地址_居委村弄
     */
    @NameField(type = DictType.Area, key = "JWCN", target = "jzdzJwcnName")
    private String jzdzJwcn;
    private String jzdzJwcnName;
    /**
     * 居住地址_详细地址
     */
    private String jzdzXxdz;
    /**
     * 居住地址
     */
    private String jzdz;
    /**
     * 档案登记时间 日期格式:yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dadjsj;
    /**
     * 诊断
     */
    @NameField(type = DictType.DICT, key = "ZD", target = "zdName")
    private String zd;
    private String zdName;
    /**
     * 档案状态
     */
    @NameField(type = DictType.DICT, key = "GLZT", target = "daztName")
    private String dazt;
    private String daztName;
    /**
     * 家庭医生id
     */
    @DictFormat(dictType = DictType.User)
    private String jtysid;
    /**
     * 家庭医生
     */
    private String jtys;
    /**
     * 随访助手ID
     */
    /*@NameField(type = DictType.User, key = "sfzsid", target = "sfzs")*/
    @DictFormat(dictType = DictType.User)
    private String sfzsid;
    /**
     * 随访助手
     */
    private String sfzs;

    public void setCsrqAndNlFromIdCard() throws ParseException {
        if (this.sfzh == null || this.sfzh.length() != 18) {
            throw new IllegalArgumentException("身份证号格式不正确");
        }

        // 从身份证号中提取出生日期
        String birthDateStr = this.sfzh.substring(6, 14);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date birthDate;
        try {
            birthDate = dateFormat.parse(birthDateStr);
        } catch (ParseException e) {
            throw new RuntimeException("出生日期解析失败", e);
        }

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

        int age = currentYear - birthYear;
        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
            age--;
        }

        // 赋值给字段
        this.nl = age;
        // 提取性别信息
        char genderCode = this.sfzh.charAt(16);
        this.xb = (genderCode % 2 == 0) ? "2" : "1";
    }

    public void updateJzdz() {
        this.jzdz = String.join(" ",
                Optional.ofNullable(jzdzShengName).orElse(""),
                Optional.ofNullable(jzdzShiName).orElse(""),
                Optional.ofNullable(jzdzQxName).orElse(""),
                Optional.ofNullable(jzdzJdName).orElse(""),
                Optional.ofNullable(jzdzJwcnName).orElse(""),
                Optional.ofNullable(jzdzXxdz).orElse(""));
    }
}
