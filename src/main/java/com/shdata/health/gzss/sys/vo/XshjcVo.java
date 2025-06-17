package com.shdata.health.gzss.sys.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 血生化检查  VO
 *
 * @author 丁文韬
 * @date 2024-07-29
 */
@Data
@NoArgsConstructor
@Accessors(chain = false)
@ExcelIgnoreUnannotated
public class XshjcVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
//    @ExcelProperty(value = "主键ID", index = 0)
//    private String id;

    @ExcelProperty(value = "检验日期", index = 0)
    private String testDate;

    @ExcelProperty(value = "类别", index = 1)
    private String category;

    @ExcelProperty(value = "病员号", index = 2)
    private String patientNumber;

    @ExcelProperty(value = "姓名", index = 3)
    private String name;

    @ExcelProperty(value = "性别", index = 4)
    private String gender;

    @ExcelProperty(value = "年龄", index = 5)
    private String age;

    @ExcelProperty(value = "标本", index = 6)
    private String specimen;

    @ExcelProperty(value = "检验项目", index = 7)
    private String testItem;

    @ExcelProperty(value = "结果", index = 8)
    private String result;

    @ExcelProperty(value = "提示", index = 9)
    private String hint;

    @ExcelProperty(value = "参考区间", index = 10)
    private String referenceRange;
}
