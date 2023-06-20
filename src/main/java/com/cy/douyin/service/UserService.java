package com.cy.douyin.service;

import com.cy.douyin.model.domain.UserCore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.douyin.model.response.LoginResponse;

import java.util.List;

public interface UserService extends IService<UserCore> {

    void register(String username, String password);

    LoginResponse login(String username, String password);

    void checkParam(String username,String password);

    String encryptionPassword(String password);

}
