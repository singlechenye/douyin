package com.cy.douyin.util;

import com.cy.douyin.model.response.Response;

import static com.cy.douyin.constant.ResponseConstants.*;

public class ResponseUtil {

    public static  Response success(){
        int code = SUCCESS.getCode();
        String msg = SUCCESS.getMsg();
        return new Response(code,msg);
    }

    public static Response error(int code,String msg ){

        return new Response(code,msg);
    }
}
