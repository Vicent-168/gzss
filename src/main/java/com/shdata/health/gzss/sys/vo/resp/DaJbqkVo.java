package com.shdata.health.gzss.sys.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 档案基本情况  VO
 *
 * @author xgb
 * @date 2024-07-10
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaJbqkVo implements Serializable {
    /**
     * 档案编号
     */
    private String daId;
    /**
     * 身份证号
     */
    private String sfzh;
    /**
     * 档案登记时间 日期格式:yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dadjsj;
    /**
     * 姓名
     */
    private String xm;
    /**
     * 医保卡号
     */
    private String ybkh;
    /** 性别 */
    @NameField(type = DictType.DICT,key = "XB",target = "xbName")
    private String xb;
    private String xbName;
    /**
     * 出生日期 日期格式:yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date csrq;
    /**
     * 年龄
     */
    private Integer nl;
    /**
     * 联系方式
     */
    private String lxfs;
    /**
     * 民族
     */
    @NameField(type = DictType.DICT,key = "MZ",target = "mzName")
    private String mz;
    private String mzName;
    /**
     * 其他民族
     */
    private String qtmz;
    /**
     * 职业
     */
    @NameField(type = DictType.DICT,key = "ZY",target = "zyName")
    private String zy;
    private String zyName;
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
     * 初潮年龄
     */
    private long ccnl;
    /**
     * 绝经年龄
     */
    private long jjnl;
    /**
     * 绝经前子宫切除
     */
    private String jjqzgqc;
    /**
     * 妊娠次数
     */
    private long rscs;
    /**
     * 既往骨折史
     */
    private String jwgzs;
    /**
     * 次数
     */
    private long cs;
    /**
     * 慢性疾病
     */
    @NameField(type = DictType.DICT, key = "MXJB", target = "mxjbName")
    private String mxjb;
    private String mxjbName;
    /**
     * 慢性疾病药物种类
     */
    @NameField(type = DictType.DICT, key = "MXJBYWZL", target = "mxjbywzlName")
    private String mxjbywzl;
    private String mxjbywzlName;

    /**
     * IOF骨质疏松症风险一分钟测试
     */
    private String iofcs;
    /**
     * 身高降低
     */
    @NameField(type = DictType.DICT, key = "SF", target = "sgjdName")
    private String sgjd;
    private String sgjdName;
    /**
     * 降低多少厘米
     */
    private BigDecimal jddslm;
    /**
     * 是否吸烟
     */
    @NameField(type = DictType.DICT, key = "SF", target = "sfxyName")
    private String sfxy;
    private String sfxyName;
    /**
     * 每日吸烟量
     */
    private long mrxyl;
    /**
     * 父母髋骨骨折
     */
    @NameField(type = DictType.DICT, key = "SF", target = "fmkggzName")
    private String fmkggz;
    private String fmkggzName;
    /**
     * 问答1
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd1;
    /**
     * 问答2
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd2;
    /**
     * 问答3
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd3;
    /**
     * 问答4
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd4;
    /**
     * 问答5
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd5;
    /**
     * 问答6
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd6;
    /**
     * 问答7
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd7;
    /**
     * 问答8
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd8;
    /**
     * 问答9
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd9;
    /**
     * 问答10
     */
    @DictFormat(dictType = DictType.DICT, dictKey = "SF")
    private String wd10;
    /**
     * 身高
     */
    private BigDecimal sg;
    /**
     * 体重
     */
    private BigDecimal tz;
    /**
     * BMI水平
     */
    @NameField(type = DictType.DICT, key = "BMISP", target = "bmispName")
    private String bmisp;
    private String bmispName;
    /**
     * 驼背
     */
    @NameField(type = DictType.DICT, key = "SF", target = "tbName")
    private String tb;
    private String tbName;
    /**
     * 脊柱压痛叩击痛
     */
    @NameField(type = DictType.DICT, key = "SF", target = "jzytzjtName")
    private String jzytzjt;
    private String jzytzjtName;
    /**
     * 上臂前端_密度
     */
    private BigDecimal sbMd;
    /**
     * 上臂前端_T值
     */
    private BigDecimal sbTz;
    /**
     * 足踝部_密度
     */
    private BigDecimal zhbMd;
    /**
     * 足踝部_T值
     */
    private BigDecimal zhbTz;
    /**
     * 诊断
     */
    @NameField(type = DictType.DICT, key = "ZD", target = "zdName")
    private String zd;
    private String zdName;
    /**
     * 其他疾病
     */
    private String qtjb;
    /**
     * Major osteoporotic
     */
    private String majorO;
    /**
     * Hip Fracture
     */
    private String hipF;
    /**
     * 胸腰椎摄片-椎体骨折评估 GZPG
     */
    @NameField(type = DictType.DICT, key = "GZPG", target = "gzpgName")
    private String gzpg;
    private String gzpgName;
    /**
     * 胸椎骨折  GZPG
     */
    @NameField(type = DictType.DICT, key = "GZPG", target = "xzgzName")
    private String xzgz;
    private String xzgzName;
    /**
     * 胸椎具体部位
     */
    private String xzjtbw;
    /**
     * 胸椎摄片日期 日期格式:yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xzsprq;
    /**
     * 腰椎骨折
     */
    private String yzgz;
    /**
     * 腰椎具体部位
     */
    private String yzjtbw;
    /**
     * 腰椎摄片日期 日期格式:yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date yzsprq;
    /**
     * 用药情况
     */
    @NameField(type = DictType.DICT, key = "GBYWFL", target = "yyqkName")
    private String yyqk;
    private String yyqkName;

    /**
     * 饮酒情况
     */
    @NameField(type = DictType.DICT, key = "YJQK", target = "yjzlName")
    private String yjzl;
    private String yjzlName;

//    /** 饮酒频率 */
//    private long hjpl;
//    /** 饮酒每次量 */
//    private long hjmcl;
    /**
     * 档案状态
     */
    @NameField(type = DictType.DICT, key = "GLZT", target = "daztName")
    private String dazt;
    private String daztName;
    /**
     * 档案状态登记时间 日期格式:yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date daztdjsj;
    /**
     * 家庭医生id
     */
    @DictFormat(dictType = DictType.User )
    private String jtysid;
    /**
     * 家庭医生
     */
    private String jtys;
    /**
     * 随访助手ID
     */
    @DictFormat(dictType = DictType.User )
    //@NameField(type = DictType.User,  target = "sfzs")
    private String sfzsid;
    /**
     * 随访助手
     */
    private String sfzs;
    /**
     * 管理机构
     */
    @NameField(type = DictType.Yljg,target = "gljgName")
    private String gljg;
    private String gljgName;
    /**
     * 登记者ID
     */
    @DictFormat(dictType = DictType.User )
    //@NameField(type = DictType.User, target = "djz")
    private String djzid;
    /**
     * 登记者
     */
    private String djz;
    /**
     * 删除标记 0表示未删除，1表示删除
     */
    private String delFlag;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改者
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 标识
     */
    private String remarks;

    //根据身份证号自动给出生日期和年龄字段赋值
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
        this.csrq = birthDate;
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

    /**
     * 计算BMI并确定bmisp的值
     */
    public void calculateBmiAndDetermineBmisp() {
        if (sg == null || tz == null || sg.compareTo(BigDecimal.ZERO) == 0) {
            bmisp = null; // 处理无效输入
            return;
        }

        // 将身高从cm转换为m
        BigDecimal heightInMeters = sg.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        // 计算BMI值
        BigDecimal bmi = tz.divide(heightInMeters.multiply(heightInMeters), 2, RoundingMode.HALF_UP);

        // 确定bmisp的值
        if (bmi.compareTo(new BigDecimal("18.5")) < 0) {
            bmisp = "01"; // BMI < 18.5
        } else if (bmi.compareTo(new BigDecimal("24")) < 0) {
            bmisp = "02"; // 18.5 <= BMI < 24
        } else if (bmi.compareTo(new BigDecimal("28")) < 0) {
            bmisp = "03"; // 24 <= BMI < 28
        } else {
            bmisp = "04"; // BMI >= 28
        }
    }


}
