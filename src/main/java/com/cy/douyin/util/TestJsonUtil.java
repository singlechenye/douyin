package com.cy.douyin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 86147
 * create  15/6/2023 上午10:02
 */
public class TestJsonUtil {

    public static <T> void showJson(T t) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(t);
        System.out.println(jsonStr);
    }
}
