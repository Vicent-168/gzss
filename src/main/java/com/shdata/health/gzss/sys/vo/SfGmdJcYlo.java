package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 随访骨密度检查一览  VO
 *
 * @author dwt
 * @date 2024-07-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfGmdJcYlo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;
    /** 档案ID */
    private String daId;
    /** DXA骨密度检查_检查日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dxaJcrq;
    /** 检查医疗机构 */
    private String jcyljg;
    /** 身份证号 */
    private String sfzh;
    /** 姓名 */
    private String xm;
    /** 医保卡号 */
    private String ybkh;
    /** 性别 */
    @NameField(type = DictType.DICT,key = "XB",target = "xbName")
    private String xb;
    private String xbName;
    /** 出生日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date csrq;
    /** 年龄 */
    private Integer nl;
    /** 联系方式 */
    private String lxfs;

    /** T值L1 */

    private BigDecimal tzL1;
    /** T值L2 */

    private BigDecimal tzL2;
    /** T值L3 */

    private BigDecimal tzL3;
    /** T值L4 */

    private BigDecimal tzL4;
    /** T值L1-L4 */

    private BigDecimal tzL1L4;
    /** T值全髋 */

    private BigDecimal tzQh;
    /** T值股骨颈 */

    private BigDecimal tzGgj;
    /** 随访方式 */
    @NameField(type = DictType.DICT,key = "SFFS",target = "sffsName")
    private String sffs;
    private String sffsName;
    /** 随访医生ID */
    //@NameField(type = DictType.User, target = "sfys")
    @DictFormat(dictType = DictType.User)
    private String sfysid;
    /** 随访医生 */
    private String sfys;
    /** 管理机构 */
    @NameField(type = DictType.Yljg, target = "gljgName")
    private String gljg;
    private String gljgName;

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
