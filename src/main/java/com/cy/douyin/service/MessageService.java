package com.cy.douyin.service;

import com.cy.douyin.model.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
* @author 86147
* @description 针对表【message】的数据库操作Service
* @createDate 2023-06-19 14:53:19
*/
public interface MessageService extends IService<Message> {

    void postMessage(Integer toUserId,String content) throws JsonProcessingException;

    List<Message> messageList(Integer toUserId);

    List<Message> newMessageListByCache(Integer toUserId) throws JsonProcessingException;

}
