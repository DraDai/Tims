package com.tims.zc.Controller;

import com.teacher.pojo.Result;
import com.teacher.pojo.User;
import com.teacher.utils.JwtUtils;
import com.tims.zc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("登陆账号id {}", user.getTno());
        user = userService.login(user.getTno(), user.getPwd());
        if (user == null) {
            return Result.error("登陆失败");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getTno());
        String jwt = JwtUtils.generateJwt(claims);
        return Result.success(jwt);
    }
}
