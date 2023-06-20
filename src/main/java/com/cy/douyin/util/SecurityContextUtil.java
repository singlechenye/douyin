package com.cy.douyin.util;

import com.cy.douyin.model.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 86147
 * create  16/6/2023 下午2:08
 */
public class SecurityContextUtil {
    public static Integer getUserId() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser().getId();
    }

    public static String getUsername() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
