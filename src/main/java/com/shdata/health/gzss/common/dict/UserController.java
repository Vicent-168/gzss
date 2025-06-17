package com.shdata.health.gzss.common.dict;

import com.shdata.health.common.dict.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 用户字典控制器

**/
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 通过用户ID获取对应的中文名称
     */
    @GetMapping("/findById")
    public ResponseEntity<String> findById(String id) {
        return ResponseEntity.ok(userService.getName(id));
    }
}
