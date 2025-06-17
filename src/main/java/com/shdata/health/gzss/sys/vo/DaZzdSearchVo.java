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
 * 档案_转诊单  搜索VO
 *
 * @author 丁文韬
 * @date 2024-07-26
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaZzdSearchVo extends PageSearch<DaZzdVo> {
    /** 姓名/身份证/社保卡号 */
    private String keyword;
    /** 档案id */
    private String daId;
    /** 管理医疗机构 */
    private String glyljg;
    //起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  endDate;
    //转诊医生
    private String zzysid;
    /** 转诊状态 01转出 02 回归*/
    private List<String> zzztlist;
    /** 转诊类别  01院内康复  02向上转诊*/
    private List<String> zzlblist;
    //管理状态 1 在管 2暂存 9转归
    private List<String> glztlist;
    /** 转出原因  */
    private String zcyy;
    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of( "zzrq");
    }
    public String getKeyword() {
        return ValueUtil.like(keyword);
    }
    public String getZcyy() {
        return ValueUtil.like(zcyy);
    }
}
