package com.cy.douyin.controller;

import com.cy.douyin.model.domain.UserCore;
import com.cy.douyin.model.domain.UserInfo;
import com.cy.douyin.model.response.InfoResponse;
import com.cy.douyin.model.response.LoginResponse;
import com.cy.douyin.model.response.Response;
import com.cy.douyin.service.UserInfoService;
import com.cy.douyin.util.ResponseUtil;
import com.cy.douyin.service.UserService;
import com.cy.douyin.util.TestJsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 包括user的登录注册以及登出还有修改信息的接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserInfoService userInfoService;

    @GetMapping
    public InfoResponse info(@RequestParam String token) throws JsonProcessingException {
        UserInfo userInfo = userInfoService.info(token);
        return new InfoResponse(0, "获取成功", userInfo);
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestParam String username,@RequestParam String password){
        userService.register(username, password);
        return userService.login(username,password);
    }

    /**
     * 登录成功后会返回一个jwt token,前端进行存储后可以携带此参数实现单点登录
     * @return Response<User>
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestParam String username,@RequestParam String password) throws JsonProcessingException {
        return userService.login(username, password);
    }


}
