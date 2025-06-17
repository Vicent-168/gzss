package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.base.PageSearch;
import com.shdata.health.common.utils.ValueUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * 档案随访提醒  搜索VO
 *
 * @author 丁文韬
 * @date 2024-08-06
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaSftxSearchVo extends PageSearch<DaSftxVo> {

    /**
     * 姓名/身份证/社保卡号
     */
    private String keyword;
    /**
     * 检查医疗机构
     */
    private String glyljg;
    /**
     * 责任医生
     */
    private String zrys;
    /**
     * 未访时间
     * 01 代表3个月到半年
     * 02 代表半年到1年
     * 03 代表一年以上
     */
    private List<String>  wfsjCodes;
    /**
     * 提醒内容
     * 01 代表DXA骨密度检擦
     * 02 代表血生化检查
     */
    private List<String> txnrCodes;


    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of( "da_id", "txnr");
    }

    public String getKeyword() {
        return ValueUtil.like(keyword);
    }
}
