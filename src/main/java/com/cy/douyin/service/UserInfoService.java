package com.cy.douyin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.douyin.model.domain.UserInfo;

/**
* @author 86147
* @description 针对表【user_info】的数据库操作Service
* @createDate 2023-06-13 15:00:24
*/
public interface UserInfoService extends IService<UserInfo> {

    public UserInfo info(String token);

}
