package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.base.PageSearch;
import com.shdata.health.common.utils.ValueUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 随访服药信息  搜索VO
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfFyxxSearchVo extends PageSearch<SfFyxxVo> {

    /**
     * 姓名/身份证/社保卡号
     */
    private String keyword;
    /**
     * 开处方医疗机构
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
    //药物不良反应
    private String ywblfy;

    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("cfrq");
    }

    public String getKeyword() {
        return ValueUtil.like(keyword);
    }
}
