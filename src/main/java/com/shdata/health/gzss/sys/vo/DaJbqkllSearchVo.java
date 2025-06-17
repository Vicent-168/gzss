package com.shdata.health.gzss.sys.vo;

import com.shdata.health.common.base.PageSearch;
import com.shdata.health.gzss.sys.vo.resp.DaJbqkllVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 档案_基本情况履历表  搜索VO
 *
 * @author dwt
 * @date 2024-07-12
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DaJbqkllSearchVo extends PageSearch<DaJbqkllVo> {


    /**
     * 允许排序的字段
     */
    @Override
    public Set<String> orderColumns() {
        return Set.of("llid", "da_id", "sfzh", "dadjsj", "xm", "ybkh", "xb", "csrq", "lxfs", "mz", "qtmz", "zy", "jzdz_sheng", "jzdz_shi", "jzdz_qx", "jzdz_jd", "jzdz_jwcn", "jzdz_xxdz", "ccnl", "jjnl", "jjqzgqc", "rscs", "jwgzs", "cs", "mxjb", "mxjbywzl", "iofcs", "sgjd", "jddslm", "sfxy", "mrxyl", "fmkggz", "yjzl", "hjpl", "hjmcl", "wd1", "wd2", "wd3", "wd4", "wd5", "wd6", "wd7", "wd8", "wd9", "wd10", "sg", "tz", "bmisp", "tb", "jzytzjt", "sb_md", "sb_tz", "zhb_md", "zhb_tz", "zd", "qtjb", "major_o", "hip_f", "gzpg", "xzgz", "xzjtbw", "xzsprq", "yzgz", "yzjtbw", "yzsprq", "yyqk", "dazt", "sfzsid", "sfzs", "gljg", "djzid", "djz");
    }
}
