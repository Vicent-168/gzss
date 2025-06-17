package com.shdata.health.gzss.common.dict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class YljgService implements com.shdata.health.common.dict.YljgService{

    @Autowired
    private YljgMapper yljgMapper;

    @Override
    public String getName(String yljgdm) {
        return yljgMapper.getName(yljgdm);
    }
}
