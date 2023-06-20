package com.cy.douyin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.douyin.model.domain.Message;
import com.cy.douyin.service.MessageService;
import com.cy.douyin.mapper.MessageMapper;
import com.cy.douyin.util.ExceptionUtil;
import com.cy.douyin.util.SecurityContextUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author 86147
* @description 针对表【message】的数据库操作Service实现
* @createDate 2023-06-19 14:53:19
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void postMessage(Integer toUserId, String content) throws JsonProcessingException {
        Integer userId = SecurityContextUtil.getUserId();
        Message message = new Message();
        message.setTo_user_id(toUserId);
        message.setFrom_user_id(userId);
        message.setContent(content);
        boolean save = this.save(message);
        if (!save) ExceptionUtil.throwAppErr("消息发送失败");
        //将消息复制一份存在redis里面,下次推送新聊天记录直接从redis取
        Date date = new Date();
        long timestamp = date.getTime() / 1000;
        message.setCreate_time((int) timestamp);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(message);
        redisTemplate.opsForList().leftPush(userId+":"+toUserId,jsonString);

    }

    @Override
    public List<Message> messageList(Integer toUserId) {
        Integer userId = SecurityContextUtil.getUserId();
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("to_user_id", userId)
                .eq("from_user_id", toUserId)
                .or()
                .eq("to_user_id", toUserId)
                .eq("from_user_id", userId)
                .orderByAsc("create_time");
        while(true) {
            // 从 List 集合右侧弹出元素
            String s = redisTemplate.opsForList().rightPop(toUserId + ":" + userId);
            // 如果弹出值为 null，则说明当前列表为空；退出循环
            if (s == null) {
                break;
            }
        }
        return timeToInteger(this.list(queryWrapper));
    }

    @Override
    public List<Message> newMessageListByCache(Integer toUserId) throws JsonProcessingException {
        ArrayList<Message> messages = new ArrayList<>();
        Integer userId = SecurityContextUtil.getUserId();
        while(true) {
            // 从 List 集合右侧弹出元素
            String s = redisTemplate.opsForList().rightPop(toUserId + ":" + userId);
            // 如果弹出值为 null，则说明当前列表为空；退出循环
            if (s == null) {
                break;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Message message = objectMapper.readValue(s, Message.class);
            messages.add(message);
        }
        return messages;
    }

    private List<Message> timeToInteger(List<Message> messageList) {
        return messageList.stream().map(message -> {
            Date createTime = message.getCreateTime();
            if (createTime != null) {
                long timestamp = createTime.getTime() / 1000;
                message.setCreate_time((int) timestamp);
            }
            return message;
        }).collect(Collectors.toList());
    }


}




