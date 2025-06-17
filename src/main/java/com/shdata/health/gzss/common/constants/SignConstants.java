package com.shdata.health.gzss.common.constants;

/**
 * 签约常量类
 *
 * @author wangjie
 */
public class SignConstants {
    /**
     * 签约状态：0未生效
     */
    public static final String SIGN_STATUS_0 = "0";

    /**
     * 签约状态： 1生效
     */
    public static final String SIGN_STATUS_1 = "1";

    /**
     * 签约状态： 3解约
     */
    public static final String SIGN_STATUS_3 = "3";

    /**
     * 签约状态：5签约信息变更后历史数据
     */
    public static final String SIGN_STATUS_5 = "5";

    /**
     * 续约状态：6 已续约
     */
    public static final String SIGN_STATUS_6 = "6";

    /**
     * 签约状态：9 注销
     */
    public static final String SIGN_STATUS_9 = "9";


    /**
     * 实名认证类型 1 电子签核
     */
    public static final String AUTH_TYPE_1 = "1";

    /**
     * 实名认证类型 2 健康云验证码验证
     */
    public static final String AUTH_TYPE_2 = "2";

    /**
     * 实名认证类型 3 健康云便民
     */
    public static final String AUTH_TYPE_3 = "3";

    /**
     * 实名认证类型 4 健康云PDA
     */
    public static final String AUTH_TYPE_4 = "4";

    /**
     * 是否 1是
     */
    public static final String YES = "1";

    /**
     * 是否 0否
     */
    public static final String NO = "0";

    /**
     * 可提前续约的月数：可提前一个季度续约
     */
    public static final int RENEWABLE_MONTH_LIMIT = 3;

    /**
     * 超过1年未续约，不能再续约
     */
    public static final int RENEWABLE_YEAR_LIMIT = 1;

    /**
     * 二三级医院变更限制： 1年后才可再次变更
     */
    public static final int HOSPITAL_CHANGE_LIMIT = 1;

    /**
     * 全量地址切分关键字
     */
    public static final String ADDRESS_SPLIT_KEY = "居委会";

    /**
     * 签约文件类型 bmp
     */
    public static final String ATTR_TYPE_BMP = "bmp";

    /**
     * 签约文件类型 pdf
     */
    public static final String ATTR_TYPE_PDF = "pdf";

    /**
     * 续约告知人-家庭医生
     */
    public static final String RENEW_GZR_DOCTOR = "1";

    /**
     * 签约协议PDF文件大小限制: 1M,  1 * 1024 * 1024 B
     */
    public static final int SIGN_PDF_MAX_SIZE = 1048576;

    /**
     * 社保卡
     */
    public static final String SIGN_CARD_SHEBAO = "0";

    /**
     * 医保卡
     */
    public static final String SIGN_CARD_YIBAO = "1";
    /**
     * 自费卡
     */
    public static final String SIGN_CARD_ZIFEI = "2";
    /**
     * 家庭医生角色code值
     */
    public static final String ROLE_DOCTOR = "doctor";

    /**
     * 签约数据来源 jky
     */
    public static final String SIGN_DATA_SOURCE_JKY = "jky";

}
