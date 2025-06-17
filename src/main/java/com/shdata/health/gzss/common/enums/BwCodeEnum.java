package com.shdata.health.gzss.common.enums;

public enum BwCodeEnum {
// 01表示 CDC_TB_SF_GMDJC表中TZ_L1_L4字段的值  02表示CDC_TB_SF_GMDJC表中TZ_QH字段的值  03表示CDC_TB_SF_GMDJC表中TZ_GGJ字段
    BW_01("01", "TZ_L1_L4"),
    BW_02("02", "TZ_QH"),
    BW_03("03", "TZ_GGJ");

    private String code;
    private String name;

    BwCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }
   public static String getNameByCode(String code) {
        //根据code获取name
        for (BwCodeEnum bwCodeEnum : BwCodeEnum.values()) {
            if (bwCodeEnum.getCode().equals(code)) {
                return bwCodeEnum.getName();
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }

}
