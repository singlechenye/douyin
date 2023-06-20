package com.cy.douyin.filter;


import com.alibaba.fastjson.JSON;
import com.cy.douyin.constant.ResponseConstants;
import com.cy.douyin.model.security.CustomUserDetails;
import com.cy.douyin.model.domain.UserCore;
import com.cy.douyin.util.ExceptionUtil;
import com.cy.douyin.util.JwtUtil;
import com.cy.douyin.util.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.cy.douyin.constant.UserConstants.REDIS_LOGIN_KEY;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Request URL: " + request.getRequestURL());
        String token = request.getParameter("token");
        if (Strings.isBlank(token)) {
            String uri = request.getRequestURI(); // 获取请求的 URI
            String[] segments = uri.split("/"); // 将 URI 按照 '/' 分隔成多个片段
            String action = segments[segments.length - 1]; // 取出 URI 的最后一段，即 action 名称
            if ("login".equals(action) || "register".equals(action) || "feed".equals(action)) { // 如果 action 是 login 或 register，放行
                filterChain.doFilter(request, response);
            }
            return;
        }
        String subject = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            subject = claims.getSubject();
        } catch (Exception e) {
            ExceptionUtil.throwAppErr(ResponseConstants.PARAMETER_ERROR);
        }

        Object cacheObject = redisCacheUtil.getCacheObject(REDIS_LOGIN_KEY + subject);
        UserCore userCore = JSON.parseObject(cacheObject.toString(), UserCore.class);


        if (Objects.isNull(userCore)) {
            ExceptionUtil.throwAppErr(ResponseConstants.NOT_LOGIN_ERROR);
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(userCore);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities() );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
