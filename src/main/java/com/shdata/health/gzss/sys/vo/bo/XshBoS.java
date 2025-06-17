package com.shdata.health.gzss.sys.vo.bo;

import com.shdata.health.common.base.PageData;
import com.shdata.health.common.serializer.jackson.DictFormat;
import com.shdata.health.common.serializer.jackson.DictType;
import com.shdata.health.gzss.common.vo.PgJgXqVo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 评估结果-血生化小页面对象
 *
 * @author dwt
 * @date 2024-10-23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class XshBoS {
    /** 检验项目  参照【字典：检验项目】 */
    @NotBlank
    @DictFormat(dictType = DictType.DICT,dictKey = "JYXM")
    private String jyxm;
    /** 显示数 */
    @NotNull
    private Long xss;
    /** 开始记录数 */
    @NotNull
    private Long ksjls;
    /** 评估id */
    private String pgId;
    /**血生化指标条数 */
    private Long total;
    /** 评估血生化指标 */
    private List<PgJgXqVo> xshBos;

    // 分页信息
    private long currPage;   // 当前页码
    private long pageSize;   // 每页显示数
    private long totalPages; // 总页数

    public PageData<PgJgXqVo> toPageData() {
        long currPage = (this.ksjls - 1) / this.xss + 1; // 计算当前页码
        PageData<PgJgXqVo> pageData = new PageData<>();
        pageData.setCurrPage(currPage);
        pageData.setPageSize(this.xss);
        pageData.setTotal(this.total);
        pageData.setData(this.xshBos);
        return pageData;
    }

}
