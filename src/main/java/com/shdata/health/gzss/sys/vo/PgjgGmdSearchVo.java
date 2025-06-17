package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.base.PageSearch;
import lombok.Data;
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
@Accessors(chain = true)
public class PgjgGmdSearchVo extends PageSearch<SfGmdjcVo> {

    /** 档案ID */
    private String daId;
    /** 起始时间 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmdJcks;
    /** 结束时间 日期格式:yyyy-MM-dd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  gmdJcjs;
    //骨密度显示数，默认为5
    private long gmdXss = 5;
    //骨密度开始记录数，默认为1
    private long gmdKsjls = 1;

    /**
     * 初始化默认值的方法
     */
    public void initDefaults() {
        // 设置默认的起始时间为当前时间前推一年
        if (this.gmdJcks == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -1);
            this.gmdJcks = calendar.getTime();
        }

        // 设置默认的结束时间为当前系统时间
        if (this.gmdJcjs == null) {
            this.gmdJcjs = new Date();
        }

        // 骨密度显示数的默认值（对应页面大小）
        if (this.gmdXss <= 0) {
            this.gmdXss = 5;
        }

        // 骨密度开始记录数的默认值（对应开始记录数）
        if (this.gmdKsjls <= 0) {
            this.gmdKsjls = 1;
        }

        // 计算当前页码
        long currPage = (this.gmdKsjls - 1) / this.gmdXss + 1;

        // 将 pageSize 和 currPage 赋值给 PageSearch
        this.setPageSize(this.gmdXss);
        this.setCurrPage(currPage);
    }


    @Override
    public Set<String> orderColumns() {
        return Set.of("DXA_JCRQ");
    }
}
