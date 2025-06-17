package com.shdata.health.gzss.sys.vo.resp;

import com.shdata.health.gzss.sys.vo.SfFyxxVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 共同子页面_随访服药史  SfFyxxDto
 *
 * @author dwt
 * @date 2024-07-19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfFyxxDto {
    //处方日期
    private String cfrq;

    private List<SfFyxxVo> sfFyxxVos;
}
