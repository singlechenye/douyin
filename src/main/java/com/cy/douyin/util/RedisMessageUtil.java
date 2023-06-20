package com.cy.douyin.util;

/**
 * @Author : JCccc
 * @CreateTime : 2019-1-2
 * @Description :
 * @Point: Keep a good mood
 **/

import org.springframework.stereotype.Component;

/**
 * redis消息处理器
 */
@Component
public class RedisMessageUtil {

    /**
     * 接收到消息的方法，message就是指从主题获取的消息，主题配置在RedisMessageListener配置类做配置
     * @param message
     */
    public void receiveMessage(String message) {

    }
}