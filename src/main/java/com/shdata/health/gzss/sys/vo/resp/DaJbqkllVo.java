package com.shdata.health.gzss.sys.vo.resp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 档案_基本情况履历表  VO
 *
 * @author dwt
 * @date 2024-07-12
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaJbqkllVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 履历编号 */
    private String llid;
    /** 档案编号 */
    @NotBlank
    private String daId;
    /** 身份证号 */
    @NotBlank
    private String sfzh;
    /** 档案登记时间 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dadjsj;
    /** 姓名 */
    @NotBlank
    private String xm;
    /** 医保卡号 */
    @NotBlank
    private String ybkh;
    /** 性别 */
    @NotBlank
    private String xb;
    /** 出生日期 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date csrq;
    /** 联系方式 */
    @NotBlank
    private String lxfs;
    /** 民族 */
    @NotBlank
    private String mz;
    /** 其他民族 */
    @NotBlank
    private String qtmz;
    /** 职业 */
    @NotBlank
    private String zy;
    /** 居住地址_省 */
    @NotBlank
    private String jzdzSheng;
    /** 居住地址_市 */
    @NotBlank
    private String jzdzShi;
    /** 居住地址_区县 */
    @NotBlank
    private String jzdzQx;
    /** 居住地址_街道 */
    @NotBlank
    private String jzdzJd;
    /** 居住地址_居委村弄 */
    @NotBlank
    private String jzdzJwcn;
    /** 居住地址_详细地址 */
    @NotBlank
    private String jzdzXxdz;
    /** 初潮年龄 */
    @NotNull
    private long ccnl;
    /** 绝经年龄 */
    @NotNull
    private long jjnl;
    /** 绝经前子宫切除 */
    @NotBlank
    private String jjqzgqc;
    /** 妊娠次数 */
    @NotNull
    private long rscs;
    /** 既往骨折史 */
    @NotBlank
    private String jwgzs;
    /** 次数 */
    @NotNull
    private long cs;
    /** 慢性疾病 */
    @NotBlank
    private String mxjb;
    /** 慢性疾病药物种类 */
    @NotBlank
    private String mxjbywzl;
    /** IOF骨质疏松症风险一分钟测试 */
    @NotBlank
    private String iofcs;
    /** 身高降低 */
    @NotBlank
    private String sgjd;
    /** 降低多少厘米 */
    @NotNull
    private long jddslm;
    /** 是否吸烟 */
    @NotBlank
    private String sfxy;
    /** 每日吸烟量 */
    @NotNull
    private long mrxyl;
    /** 父母髋骨骨折 */
    @NotBlank
    private String fmkggz;
    /** 饮酒情况 */
    @NotBlank
    private String yjzl;
    /** 饮酒频率 */
    @NotNull
    private long hjpl;
    /** 饮酒每次量 */
    @NotNull
    private long hjmcl;
    /** 问答1 */
    @NotBlank
    private String wd1;
    /** 问答2 */
    @NotBlank
    private String wd2;
    /** 问答3 */
    @NotBlank
    private String wd3;
    /** 问答4 */
    @NotBlank
    private String wd4;
    /** 问答5 */
    @NotBlank
    private String wd5;
    /** 问答6 */
    @NotBlank
    private String wd6;
    /** 问答7 */
    @NotBlank
    private String wd7;
    /** 问答8 */
    @NotBlank
    private String wd8;
    /** 问答9 */
    @NotBlank
    private String wd9;
    /** 问答10 */
    @NotBlank
    private String wd10;
    /** 身高 */
    @NotNull
    private long sg;
    /** 体重 */
    @NotNull
    private long tz;
    /** BMI水平 */
    @NotNull
    private long bmisp;
    /** 驼背 */
    @NotBlank
    private String tb;
    /** 脊柱压痛叩击痛 */
    @NotBlank
    private String jzytzjt;
    /** 上臂前端_密度 */
    @NotNull
    private long sbMd;
    /** 上臂前端_T值 */
    @NotNull
    private long sbTz;
    /** 足踝部_密度 */
    @NotNull
    private long zhbMd;
    /** 足踝部_T值 */
    @NotNull
    private long zhbTz;
    /** 诊断 */
    @NotBlank
    private String zd;
    /** 其他疾病 */
    @NotBlank
    private String qtjb;
    /** Major osteoporotic */
    @NotBlank
    private String majorO;
    /** Hip Fracture */
    @NotBlank
    private String hipF;
    /** 胸腰椎摄片-椎体骨折评估 */
    @NotBlank
    private String gzpg;
    /** 胸椎骨折 */
    @NotBlank
    private String xzgz;
    /** 胸椎具体部位 */
    @NotBlank
    private String xzjtbw;
    /** 胸椎摄片日期 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date xzsprq;
    /** 腰椎骨折 */
    @NotBlank
    private String yzgz;
    /** 腰椎具体部位 */
    @NotBlank
    private String yzjtbw;
    /** 腰椎摄片日期 日期格式:yyyy-MM-dd */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date yzsprq;
    /** 用药情况 */
    @NotBlank
    private String yyqk;
    /** 档案状态 */
    @NotBlank
    private String dazt;
    /** 档案状态登记时间 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date daztdjsj;
    /** 家庭医生id */
    private String jtysid;
    /** 家庭医生 */
    private String jtys;
    /** 随访助手ID */
    @NotBlank
    private String sfzsid;
    /** 随访助手 */
    @NotBlank
    private String sfzs;
    /** 管理机构 */
    @NotBlank
    private String gljg;
    /** 登记者ID */
    @NotBlank
    private String djzid;
    /** 登记者 */
    @NotBlank
    private String djz;
}
