package com.cy.douyin.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.douyin.constant.ResponseConstants;
import com.cy.douyin.mapper.UserInfoMapper;
import com.cy.douyin.model.domain.UserCore;
import com.cy.douyin.model.domain.UserInfo;
import com.cy.douyin.model.security.CustomUserDetails;
import com.cy.douyin.service.UserInfoService;

import com.cy.douyin.util.ExceptionUtil;
import com.cy.douyin.util.JwtUtil;
import com.cy.douyin.util.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.security.auth.Subject;

import static com.cy.douyin.constant.UserConstants.REDIS_LOGIN_KEY;

/**
* @author 86147
* @description 针对表【user_info】的数据库操作Service实现
* @createDate 2023-06-13 15:00:24
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService {
    @Resource
    private RedisCacheUtil redisCacheUtil;
    @Override
    public UserInfo info(String token) {
        String subject=null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            subject = claims.getSubject();
        } catch (Exception e) {
            ExceptionUtil.throwAppErr(ResponseConstants.PARAMETER_ERROR);
        }

        Object cacheObject = redisCacheUtil.getCacheObject(REDIS_LOGIN_KEY + subject);
        UserCore userCore = JSON.parseObject(cacheObject.toString(), UserCore.class);
        int user_id = userCore.getId();
        return this.getById(user_id);
    }
}




