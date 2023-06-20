package com.cy.douyin.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.douyin.constant.ResponseConstants;
import com.cy.douyin.filter.JwtSecurityFilter;
import com.cy.douyin.mapper.UserMapper;
import com.cy.douyin.model.domain.UserCore;
import com.cy.douyin.model.security.CustomUserDetails;
import com.cy.douyin.model.response.Response;
import com.cy.douyin.util.ExceptionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Objects;

import static com.cy.douyin.constant.ResponseConstants.*;


@Configuration
public class SecurityConfig {

    @Resource
    private  JwtSecurityFilter jwtSecurityFilter;

    @Resource
    private UserMapper userMapper;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                QueryWrapper<UserCore> userQueryWrapper = new QueryWrapper<>();
                userQueryWrapper.eq("username",username);
                UserCore userCore = userMapper.selectOne(userQueryWrapper);
                if (Objects.isNull(userCore)) ExceptionUtil.throwAppErr(ResponseConstants.LOGIN_NOT_EXIST_ERROR);
                return new CustomUserDetails(userCore);
            }
        };
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .sessionManagement()
                .disable()
                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(200);
                    response.setContentType("application/json");
                    Response objectResponse = new Response(AUTH_LACK_ERROR.getCode(), AUTH_LACK_ERROR.getMsg());
                    String s = JSON.toJSONString(objectResponse);
                    response.getWriter().write(s);
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(200);
                    response.setContentType("application/json");
                    Response objectResponse = new Response(AUTH_CHECK_ERROR.getCode(),AUTH_CHECK_ERROR.getMsg());
                    String s = JSON.toJSONString(objectResponse);
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(s);
                });

        return httpSecurity.build();
    }

}
