package com.cy.douyin.util;

import com.cy.douyin.constant.ResponseConstants;
import com.cy.douyin.model.exception.AppException;

public class ExceptionUtil {
    public static void throwAppErr(ResponseConstants responseConstants){
        int code = responseConstants.getCode();
        String msg = responseConstants.getMsg();
        throw new AppException(code,msg);
    }

    public static void throwAppErr(String msg){
        throw new AppException(600,msg);
    }
}
