package com.shdata.health.gzss.common.dict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AreaService implements com.shdata.health.common.dict.AreaService{

    @Autowired
    private CdicAreaMapper cdicAreaMapper;

    @Override
    public String getName(String area) {
        return cdicAreaMapper.getName(area);
    }
}
