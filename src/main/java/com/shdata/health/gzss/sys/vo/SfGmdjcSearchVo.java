package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.base.PageSearch;
import com.shdata.health.common.utils.ValueUtil;
import com.shdata.health.gzss.sys.vo.bo.TsB0;
import com.shdata.health.gzss.sys.vo.bo.TzBo;
import com.shdata.health.gzss.sys.vo.bo.YFbo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 随访_骨密度检查  搜索VO
 *
 * @author dwt
 * @date 2024-07-16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfGmdjcSearchVo extends PageSearch<SfGmdjcVo> {
    /**
     * 档案id
     */
    private String daId;

    /**
     * 姓名/身份证/社保卡号
     */
    private String keyword;
    /**
     * 检查医疗机构
     */
    private String jcyljg;
    //起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    //不同部位的检查指标的集合
    // 01表示 CDC_TB_SF_GMDJC表中TZ_L1_L4字段的值  02表示CDC_TB_SF_GMDJC表中TZ_QH字段的值  03表示CDC_TB_SF_GMDJC表中TZ_GGJ字段
    //bwCodes 可传 "01","02","03","01""02","01""03","02" "03","01""02" "03",null 8种情况
    private List<String> bwCodes;
    //传与或条件 01表示与 上述bwCodes检查部分的多选条件用and连接，即bwCodes内所包含的检测字段的组合都要满足下面tzCodes条件组合的T值范围
    // 02表示或 bwCodes检查部分的多选条件用OR连接,上述任何一个检查部分所代表字段的值满足下面任何一个选定的T值范围即可；
    private String yfCode;
    //传T值对象  01 对应 -1<=T<=1 02表示-2.5<=T<=-1  03表示 T<-2.5 T值的多选组合
    // 多选组合有 "01","02","03", "01""02","01""03","02" "03","01""02" "03",null 8种情况
    // 01 -1<=T<=1
    // 02  -2.5<=T<=-1,
    // 03  T<-2.5
    // "01""02"  -2.5<=T<=1
    // "01""03"  -T<-2.5或-1<=T<=1
    // "02""03"  T<=-1
    // "01""02""03"  T<=1
    private List<String> tzCodes;
    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("dxa_jcrq");
    }

    public String getKeyword() {
        return ValueUtil.like(keyword);
    }
}
