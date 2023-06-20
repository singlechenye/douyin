package com.cy.douyin.model.response;

import com.cy.douyin.model.domain.Comment;
import lombok.Data;

/**
 * @author 86147
 * create  17/6/2023 下午5:24
 */
@Data
public class CommentResponse extends Response{
    private Comment comment;



}
