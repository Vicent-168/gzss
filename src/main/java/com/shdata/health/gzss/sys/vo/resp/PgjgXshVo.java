package com.shdata.health.gzss.sys.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shdata.health.common.mybatis.NameField;
import com.shdata.health.common.serializer.jackson.DictType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 评估结果  血生化检测PgjgXshDto
 *
 * @author dwt
 * @date 2024-07-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PgjgXshVo {
    /** 详情ID */
    private String xqId;
    /** 档案id */
    private String daId;
    /** 评估id */
    private String pgId;
    /** 检测日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jyrq;
    @NameField(type = DictType.DICT,key = "JYXM",target = "jyxmName")
    private String jyxm;
    private String jyxmName;
    //检测数值
    private String jysz;


}
