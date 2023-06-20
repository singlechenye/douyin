package com.cy.douyin.controller;

import com.cy.douyin.model.domain.Message;
import com.cy.douyin.model.response.MessageListResponse;
import com.cy.douyin.model.response.Response;
import com.cy.douyin.service.MessageService;
import com.cy.douyin.util.MessageLoadUtil;
import com.cy.douyin.util.ResponseUtil;
import com.cy.douyin.util.SecurityContextUtil;
import com.cy.douyin.util.TestJsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;



/**
 * @author 86147
 * create  19/6/2023 下午1:58
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @PostMapping("/action")
    public Response action(@RequestParam("to_user_id") Integer toUserId,
                           @RequestParam("action_type") Integer actionType,
                           @RequestParam("content") String content) throws JsonProcessingException {
        messageService.postMessage(toUserId,content);
        return ResponseUtil.success();
    }

    @GetMapping("/chat")
    public Response chatList(@RequestParam("to_user_id") Integer toUserId) throws IOException {
        MessageListResponse messageListResponse = new MessageListResponse();
        if (MessageLoadUtil.isFirst(SecurityContextUtil.getUserId())) {
            List<Message> messages = messageService.messageList(toUserId);
            messageListResponse.setMessage_list(messages);
            TestJsonUtil.showJson(messageListResponse);
        } else {
            List<Message> messages = messageService.newMessageListByCache(toUserId);
            messageListResponse.setMessage_list(messages);
            TestJsonUtil.showJson(messageListResponse);
        }
        return messageListResponse;
    }
}
