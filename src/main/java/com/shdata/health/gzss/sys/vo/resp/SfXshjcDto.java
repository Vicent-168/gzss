package com.shdata.health.gzss.sys.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 随访一览_血生化检查  Dto
 *
 * @author dwt
 * @date 2024-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshjcDto {

    @Serial
    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private String id;

    private String xm;
    /** 档案编号 */
    private String daId;
    /** 身份证号 */
    private String sfzh;
    /** 医保卡号 */
    private String ybkh;
    /** 性别 */
    private String xb;
    /** 出生日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  csrq;
    /** 年龄 */
    private Integer nl;
    /** 联系方式 */
    private String lxfs;
    /** 检验日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jyrq;
    /**总钙测定检验项目检验数值、TS值和单位*/
    private BigDecimal jysz1;
    private String ts1;
    private String dw1;
    /**无机磷测定检验项目检验数值、TS值和单位*/
    private BigDecimal jysz2;
    private String ts2;
    private String dw2;
    /**降钙素测定检验项目检验数值、TS值和单位*/
    private BigDecimal jysz3;
    private String ts3;
    private String dw3;
    /**25羟基维生素D测定检验项目检验数值、TS值和单位*/
    private BigDecimal jysz4;
    private String ts4;
    private String dw4;
    /**血清骨钙素N端中分子片段检验项目检验数值、TS值和单位*/
    private BigDecimal jysz5;
    private String ts5;
    private String dw5;
    /**β-胶原降解产物测定检验项目检验数值、TS值和单位*/
    private BigDecimal jysz6;
    private String ts6;
    private String dw6;
    /**甲状旁腺激素测定检验项目检验数值、TS值和单位*/
    private BigDecimal jysz7;
    private String ts7;
    private String dw7;
    /**总I型胶原氨基端延长测定检验项目检验数值、TS值和单位*/
    private BigDecimal jysz8;
    private String ts8;
    private String dw8;
    /**肌酐检验项目检验数值、TS值和单位*/
    private BigDecimal jysz9;
    private String ts9;
    private String dw9;
    /**丙氨酸氨基转移酶测定检验项目检验数值、TS值和单位*/
    private BigDecimal jysz10;
    private String ts10;
    private String dw10;
    /**碱性磷酸酶测定检验项目检验数值、TS值和单位*/
    private BigDecimal jysz11;
    private String ts11;
    private String dw11;
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
        this.csrq = birthDate;
        this.nl = age;
        // 提取性别信息
        char genderCode = this.sfzh.charAt(16);
        this.xb= (genderCode % 2 == 0) ? "2" : "1";
    }
}
