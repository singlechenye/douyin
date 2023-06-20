package com.cy.douyin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.douyin.model.domain.Comment;
import com.cy.douyin.model.domain.Video;

import java.util.List;

/**
* @author 86147
* @description 针对表【comment】的数据库操作Service
* @createDate 2023-06-17 16:47:41
*/
public interface CommentService  {

    List<Comment> commentList(Integer videoId);


    Comment comment(Integer videoId, Integer actionType, Integer commentId, String commentText);
}
