package com.cy.douyin.model.response;

import com.cy.douyin.model.domain.UserInfo;
import lombok.Data;

/**
 * @author 86147
 * create  13/6/2023 下午3:18
 */
public class InfoResponse extends Response{

    private UserInfo userInfo;

    public InfoResponse(int status_code, String status_msg, UserInfo userInfo) {
        super(status_code, status_msg);
        this.userInfo = userInfo;
    }

    public UserInfo getUser() {
        return userInfo;
    }

    public void setUser(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
