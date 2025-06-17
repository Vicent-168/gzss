package com.shdata.health.gzss.sys.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.gzss.sys.vo.bo.JCXMBo;
import com.shdata.health.gzss.sys.vo.bo.TsB0;
import com.shdata.health.gzss.sys.vo.bo.TzBo;
import com.shdata.health.gzss.sys.vo.bo.YFbo;
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
import java.util.List;

/**
 * 随访一览_骨密度检查  Dto
 *
 * @author dwt
 * @date 2024-07-16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfGmdJcDto {
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
    private String xb;
    /** 出生日期 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date csrq;
    /** 年龄 */
    private long nl;
    /** 联系方式 */
    private String lxfs;
//    /** 居住地址_省 */
//    private String jzdzSheng;
//    /** 居住地址_市 */
//    private String jzdzShi;
//    /** 居住地址_区县 */
//    private String jzdzQx;
//    /** 居住地址_街道 */
//    private String jzdzJd;
//    /** 居住地址_居委村弄 */
//    private String jzdzJwcn;
//    /** 居住地址_详细地址 */
//    //居住地址
//    private String jzdz;
    /** 骨密度L1 */
//    private BigDecimal gmdL1;
//    /** 骨密度L2 */
//
//    private BigDecimal gmdL2;
//    /** 骨密度L3 */
//
//    private BigDecimal gmdL3;
//    /** 骨密度L4 */
//
//    private BigDecimal gmdL4;
//    /** 骨密度L1-L4 */
//
//    private BigDecimal gmdL1L4;
//    /** 骨密度全髋 */
//
//    private BigDecimal gmdQh;
//    /** 骨密度股骨颈 */
//
//    private BigDecimal gmdGgj;
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

    private String sffs;
    /** 随访医生ID */

    private String sfysid;
    /** 随访医生 */

    private String sfys;
    /** 管理机构 */
    private String gljg;

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

        long age = currentYear - birthYear;
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
