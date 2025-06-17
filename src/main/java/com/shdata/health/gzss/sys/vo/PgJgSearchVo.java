package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.base.PageSearch;
import com.shdata.health.common.utils.DateUtils;
import com.shdata.health.common.utils.ValueUtil;
import com.shdata.health.gzss.common.utils.UserUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Param;
import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

/**
 * 评估_结果  搜索VO
 *
 * @author dwt
 * @date 2024-07-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgJgSearchVo extends PageSearch<PgJgVo> {

    /** 姓名/身份证/社保卡号 */
    private String keyword;
    /** 管理医疗机构 */
    private String glyljg;
    /** 起始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    /** 结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  endDate;
    /** 评估医生 */
    private String pgysId;
    /** 诊断结果 */
    private List<String> zdlist;
    /** 档案状态 */
    private List<String> glztlist;
    /** 评估结果  1有 2无 */
    private String pgjg;
    /** 评估结果文本，当pgjg为1时有效 */
    private String pgjgText;

    private boolean hasFilter;
    /**
     * 设置默认值的方法
     */
    public void setDefaultValues() {

        if (this.glyljg == null || this.glyljg.isEmpty()) {
            this.glyljg = UserUtils.getOrgan().getCode(); // 默认登录用户所在医疗机构
        }
        if (this.beginDate == null) {
            this.beginDate = DateUtils.addYears(new Date(), -1); // 假设默认值为1970-01-01（Unix纪元开始时间）
        }
        if (this.endDate == null) {
            this.endDate = new Date(); // 假设默认值为当前日期
        }
        if (this.zdlist == null) {
            this.zdlist = List.of("01","02","03","06","04", "05", "99"); // 假设默认值为空列表
        }
        if (this.glztlist == null) {
            this.glztlist = List.of("1","2","9");
        }
        if (this.pgjg == null || this.pgjg.isEmpty()) {
            this.pgjg = "1"; // 假设默认值做过评估的
        }
    }

    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of( "pgrq");
    }
    public String getKeyword() {
        return ValueUtil.like(keyword);
    }


    public String getPgjgText() {
        return ValueUtil.like(pgjgText);
    }


}
