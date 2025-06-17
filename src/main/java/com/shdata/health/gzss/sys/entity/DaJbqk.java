package com.shdata.health.gzss.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;

/**
 * 档案基本情况实体 对应表名CDC_TB_DA_JBQK
 *
 * @author xgb
 * @date 2024-07-10
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("CDC_TB_DA_JBQK")
public class DaJbqk extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    // 档案ID
    @TableId
    @TableField("DA_ID")
    private String daId;

    // 身份证号
    @TableField("SFZH")
    private String sfzh;

    // 档案登记时间
    @TableField("DADJSJ")
    private Date dadjsj;

    // 姓名
    @TableField("XM")
    private String xm;

    // 医保卡号
    @TableField("YBKH")
    private String ybkh;

    // 性别
    @TableField("XB")
    private String xb;

    // 出生日期
    @TableField("CSRQ")
    private Date csrq;

    // 联系方式
    @TableField("LXFS")
    private String lxfs;

    // 民族
    @TableField("MZ")
    private String mz;

    // 其他民族
    @TableField("QTMZ")
    private String qtmz;

    // 职业
    @TableField("ZY")
    private String zy;

    // 居住地址省
    @TableField("JZDZ_SHENG")
    private String jzdzSheng;

    // 居住地址市
    @TableField("JZDZ_SHI")
    private String jzdzShi;

    // 居住地址区县
    @TableField("JZDZ_QX")
    private String jzdzQx;

    // 居住地址街道
    @TableField("JZDZ_JD")
    private String jzdzJd;

    // 居住地址居委村
    @TableField("JZDZ_JWCN")
    private String jzdzJwcn;

    // 详细居住地址
    @TableField("JZDZ_XXDZ")
    private String jzdzXxdz;

    // 初次年龄
    @TableField("CCNL")
    private Integer ccnl;

    // 经济年龄
    @TableField("JJNL")
    private Integer jjnl;

    // 经济群体关心程度
    @TableField("JJQZGQC")
    private String jjqzgqc;

    // 儿女数量
    @TableField("RSCS")
    private Integer rscs;

    // 居住国家/地区
    @TableField("JWGZS")
    private String jwgzs;

    // 楼层数
    @TableField("CS")
    private Integer cs;

    // 危险疾病
    @TableField("MXJB")
    private String mxjb;

    // 危险疾病预警治疗
    @TableField("MXJBYWZL")
    private String mxjbywzl;

    // IOF-危害程度
    @TableField("IOFCS")
    private String iofcs;

    // 身高等级
    @TableField("SGJD")
    private String sgjd;

    // 身高低速免疫
    @TableField("JDDSLM")
    private Double jddslm;

    // 是否吸烟
    @TableField("SFXY")
    private String sfxY;

    // 每日吸烟量
    @TableField("MRXYL")
    private Integer mrxyl;

    // 父母是否髋骨骨折
    @TableField("FMKGGZ")
    private String fmkggz;

    // 预警治疗
    @TableField("YJZL")
    private String yjzl;

    // 危险因素1
    @TableField("WD1")
    private String wd1;

    // 危险因素2
    @TableField("WD2")
    private String wd2;

    // 危险因素3
    @TableField("WD3")
    private String wd3;

    // 危险因素4
    @TableField("WD4")
    private String wd4;

    // 危险因素5
    @TableField("WD5")
    private String wd5;

    // 危险因素6
    @TableField("WD6")
    private String wd6;

    // 危险因素7
    @TableField("WD7")
    private String wd7;

    // 危险因素8
    @TableField("WD8")
    private String wd8;

    // 危险因素9
    @TableField("WD9")
    private String wd9;

    // 危险因素10
    @TableField("WD10")
    private String wd10;

    // 身高
    @TableField("SG")
    private Double sg;

    // 体重
    @TableField("TZ")
    private Double tz;

    // BMI指数
    @TableField("BMISP")
    private Double bmisp;

    // 体表面积
    @TableField("TB")
    private String tb;

    // 骨质增生等级
    @TableField("JZYTZJT")
    private String jzytzjt;

    // 单侧股骨近端-骨密度
    @TableField("SB_MD")
    private Double sbMd;

    // 单侧股骨近端-骨体重
    @TableField("SB_TZ")
    private Double sbTz;

    // 双侧髋骨-骨密度
    @TableField("ZHB_MD")
    private Double zhbMd;

    // 双侧髋骨-骨体重
    @TableField("ZHB_TZ")
    private Double zhbTz;

    // 诊断
    @TableField("ZD")
    private String zd;

    // 其他疾病
    @TableField("QTJB")
    private String qtjb;

    // 主要药物
    @TableField("MAJOR_O")
    private String majorO;

    // 髋骨骨折
    @TableField("HIP_F")
    private String hipF;

    // 工作评估
    @TableField("GZPG")
    private String gzpg;

    // 行走辅助
    @TableField("XZGZ")
    private String xzgz;

    // 行走疼痛部位
    @TableField("XZJTBW")
    private String xzjtBW;

    // 行走疼痛评估日期
    @TableField("XZSPRQ")
    private Date xzsprq;

    // 腰痛
    @TableField("YZGZ")
    private String yzgz;

    // 腰痛部位
    @TableField("YZJTBW")
    private String yzjtBW;

    // 腰痛评估日期
    @TableField("YZSPRQ")
    private Date yzsprq;

    // 用药情况
    @TableField("YYQK")
    private String yyqk;

    // 档案状态
    @TableField("DAZT")
    private String dazt;

    // 身份证扫描ID
    @TableField("SFZSID")
    private String sfzsid;

    // 身份证扫描
    @TableField("SFZS")
    private String sfzs;

    // 管理机构
    @TableField("GLJG")
    private String gljg;

    // 登记证ID
    @TableField("DJZID")
    private String djzid;

    // 登记证
    @TableField("DJZ")
    private String djz;

    // 家庭医生ID
    @TableField("JTYSID")
    private String jtysid;

    // 家庭医生
    @TableField("JTYS")
    private String jtys;

    // 档案状态登记时间
    @TableField("DAZTDJSJ")
    private Date daztdjsj;
}
