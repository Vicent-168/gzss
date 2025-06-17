package com.shdata.health.gzss.common.dict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService  implements com.shdata.health.common.dict.UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public String getName(String id) {
        return userMapper.getName(id);
    }
}
