package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.base.PageSearch;
import com.shdata.health.common.utils.ValueUtil;
import com.shdata.health.gzss.sys.vo.bo.JCXMBo;
import com.shdata.health.gzss.sys.vo.bo.TsB0;
import com.shdata.health.gzss.sys.vo.bo.TzBo;
import com.shdata.health.gzss.sys.vo.bo.YFbo;
import com.shdata.health.gzss.sys.vo.resp.SfXshjcVo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 随访_血生化检查  搜索VO
 *
 * @author dwt
 * @date 2024-07-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfXshjcSearchVo extends PageSearch<SfXshjcVo> {


    /** 姓名/身份证/社保卡号 */
    private String keyword;
    /** 检查医疗机构 */
    private String jcyljg;
    //起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  endDate;
    //检查项目 01表示
    private List<String> jyxmCodes;
    //提示 01时表示指标正常，02时表示指标异常
    private String tscode;
    //传与或条件 yFbo对象中code值1时 jcxmBos集合中各种检查指标都用and连接，code为2所有指标用or连接
    private String yfcode;
    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("jyrq");
    }
    public String getKeyword() {
        return ValueUtil.like(keyword);
    }
}
