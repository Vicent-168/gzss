package com.shdata.health.gzss.common.vo;

import com.shdata.health.common.base.PageSearch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 医疗机构  搜索VO
 *
 * @author 丁文韬
 * @date 2024-11-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DicYljgSearchVo extends PageSearch<DicYljgVo> {

}
