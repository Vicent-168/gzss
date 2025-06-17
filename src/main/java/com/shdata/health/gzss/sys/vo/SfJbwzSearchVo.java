package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.base.PageSearch;
import com.shdata.health.common.utils.ValueUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 疾病问诊  搜索VO
 *
 * @author dwt
 * @date 2024-07-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfJbwzSearchVo extends PageSearch<SfJbwzVo> {

    /** 姓名/身份证/社保卡号 */
    private String keyword;
    /** 问诊医疗机构 */

    private String wzyljg;
    //起始时间
    private String beginDate;
    //结束时间
    private String  endDate;
    //症状
    private List<String> zzlist;
    //新发骨折
    private String xfgz;


    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of( "wzrq");
    }

    public String getKeyword() {
        return ValueUtil.like(keyword);
    }

}
