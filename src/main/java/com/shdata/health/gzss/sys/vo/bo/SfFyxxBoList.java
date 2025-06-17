package com.shdata.health.gzss.sys.vo.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SfFyxxBoList {
    private List<SfFyxxBo> boList;
}
