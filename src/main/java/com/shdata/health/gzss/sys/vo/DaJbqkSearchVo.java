package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.base.PageSearch;
import com.shdata.health.common.utils.ValueUtil;
import com.shdata.health.gzss.sys.vo.resp.DaJbqkVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 档案基本情况  搜索VO
 *
 * @author xgb
 * @date 2024-07-10
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaJbqkSearchVo extends PageSearch<DaJbqkVo> {
    /** 用于模糊查询：医保卡号/姓名/身份证号 */
    private String keyword;
    /** 管理医疗机构 */
    private String glyljg;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    /** 建档起始日期 */
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    /** 建档结束日期 */
    private Date endDate;
    //    01 骨质疏松
    //    02 骨量减少
    //    03 骨质正常
    //    04 骨质疏松伴病理性骨折
    //    05 骨量减少伴病理性骨折
    //    99 其他
    /** 诊断 */
    private List<String> zdlist;
    //    1 在管
    //    2 暂存
    //    9 转归
    /** 管理状态 */
    private List<String> daztlist;
    /** 随访助手 */
    private String sfzsid;
    /** 家庭医生id */
    private String jtysid;


    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("update_time");
    }

    public String getKeyword() {
        return ValueUtil.like(keyword);
    }
}
