package com.cy.douyin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.douyin.mapper.UserInfoMapper;
import com.cy.douyin.model.domain.UserCore;
import com.cy.douyin.model.domain.UserInfo;
import com.cy.douyin.model.response.LoginResponse;
import com.cy.douyin.model.security.CustomUserDetails;
import com.cy.douyin.service.UserService;
import com.cy.douyin.mapper.UserMapper;
import com.cy.douyin.util.JwtUtil;
import com.cy.douyin.util.RedisCacheUtil;
import com.cy.douyin.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.cy.douyin.constant.ResponseConstants.*;
import static com.cy.douyin.constant.UserConstants.*;
import static com.cy.douyin.util.ExceptionUtil.throwAppErr;
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserCore> implements UserService{

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Resource
    private UserInfoMapper userInfoMapper;


    @Override
    public void register(String username, String password){
        checkParam(username,password);
        String encryptionPassword = encryptionPassword(password);
        QueryWrapper<UserCore> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        if (this.count(userQueryWrapper)>0) throwAppErr(REGISTER_EXIST_ERROR);
        UserCore userCore = new UserCore();
        userCore.setUsername(username);
        userCore.setPassword(encryptionPassword);
        this.save(userCore);
        Integer id = userCore.getId();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName(username);
        userInfo.setFollow_count(0);
        userInfo.setFollower_count(0);
        userInfo.setTotal_favorited(0);
        userInfo.setWork_count(0);
        userInfo.setFavorite_count(0);


        int insert = userInfoMapper.insert(userInfo);
        if (insert!=1) throwAppErr(FAIL);
    }

    @Override
    public LoginResponse login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) throwAppErr(LOGIN_PASSWORD_WRONG_ERROR);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserCore userCore = userDetails.getUser();
        redisCacheUtil.setCacheObject(REDIS_LOGIN_KEY+userDetails.getUsername(), userCore,24*60*60, TimeUnit.SECONDS);
        return new LoginResponse(0,"登陆成功",JwtUtil.createJwt(userCore.getUsername()), userCore.getId());
    }

    @Override
    public void checkParam(String username,String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            throwAppErr(PARAMETER_ERROR);
        }
        if (username.length()<4) throwAppErr(PARAMETER_ERROR);
        Matcher matcher = Pattern.compile(USER_ACCOUNT_MATCH).matcher(username);
        if (matcher.find()) {
            throwAppErr(PARAMETER_ERROR);
        }

    }

    @Override
    public String encryptionPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}




