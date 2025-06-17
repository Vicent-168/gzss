package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.base.PageSearch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * 评估_结果  搜索VO
 *
 * @author dwt
 * @date 2024-07-23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgjgXshSearchVo extends PageSearch<PgJgSearchVo> {
    /** 档案ID */
    private String daId;
    //起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  endDate;
    //评估日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  pgrq;
    /** 检验项目 */
    private String jyxmCode;

    //血生化显示数，默认为5
    private long xss = 5;
    //血生化开始记录数，默认为1
    private long ksjls = 1;

    /**
     * 初始化默认值的方法
     */
    public void initDefaults() {
        // 设置默认的起始时间为当前时间前推一年
        if (this.beginDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -1);
            this.beginDate = calendar.getTime();
        }

        // 设置默认的结束时间为当前系统时间
        if (this.endDate == null) {
            this.endDate = new Date();
        }

        // 血生化显示数的默认值（对应页面大小）
        if (this.xss <= 0) {
            this.xss = 5;
        }

        // 骨密度开始记录数的默认值（对应开始记录数）
        if (this.ksjls <= 0) {
            this.ksjls = 1;
        }

        // 计算当前页码
        long currPage = (this.ksjls - 1) / this.xss + 1;

        // 将 pageSize 和 currPage 赋值给 PageSearch
        this.setPageSize(this.xss);
        this.setCurrPage(currPage);
    }
    @Override
    public Set<String> orderColumns() {
        return null;
    }
}
