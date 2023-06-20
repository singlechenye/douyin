package com.cy.douyin.model.response;

import io.swagger.models.auth.In;
import lombok.Data;


public class LoginResponse extends Response{
    /**
     * 用户鉴权token
     */
    private String token;
    /**
     * 用户id
     */
    private Integer user_id;

    public LoginResponse(int status_code, String status_msg, String token, Integer user_id) {
        super(status_code,status_msg);
        this.token = token;
        this.user_id = user_id;
    }

    public String getToken() { return token; }
    public void setToken(String value) { this.token = value; }

    public Integer getUserid() { return user_id; }
    public void setUserid(Integer value) { this.user_id = value; }
}