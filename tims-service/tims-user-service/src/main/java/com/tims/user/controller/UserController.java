package com.tims.user.controller;

import com.tims.common.core.domin.Result;
import com.tims.common.security.service.JWTService;
import com.tims.user.domin.dto.UserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/user")
@Slf4j
public class UserController {
    private final JWTService jwtService;

    public UserController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录{}", userLoginDTO);
        return Result.success(userLoginDTO);
    }
}
