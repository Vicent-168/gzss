package com.shdata.health.gzss.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.base.PageSearch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgjgXqGmdSearchVo extends PageSearch<SfGmdjcVo> {
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
 /*       // 骨密度显示数存在数据库表中
    private Long gmdXss ;
    // 骨密度开始记录数存在数据库表中
    private Long gmdKsjls ;*/
    @Override
    public Set<String> orderColumns() {
        return Set.of("dxa_jcrq");
    }
}
