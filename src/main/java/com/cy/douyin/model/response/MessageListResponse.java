package com.cy.douyin.model.response;

import com.cy.douyin.model.domain.Message;
import lombok.Data;

import java.util.List;

/**
 * @author 86147
 * create  19/6/2023 下午3:23
 */
@Data
public class MessageListResponse extends Response{

    private List<Message> message_list;
}
