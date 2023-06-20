package com.cy.douyin.util;

import com.cy.douyin.model.domain.UserCore;

public class UserUtil {
    public static UserCore getSafetyUser(UserCore userCore){
        userCore.setPassword(null);
        userCore.setIs_delete(null);
        return userCore;
    }
}
