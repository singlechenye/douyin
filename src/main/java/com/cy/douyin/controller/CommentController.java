package com.cy.douyin.controller;

import com.cy.douyin.model.domain.Comment;
import com.cy.douyin.model.domain.Video;
import com.cy.douyin.model.response.CommentListResponse;
import com.cy.douyin.model.response.CommentResponse;
import com.cy.douyin.model.response.Response;
import com.cy.douyin.service.CommentService;
import com.cy.douyin.util.ResponseUtil;
import com.cy.douyin.util.TestJsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86147
 * create  17/6/2023 下午4:42
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/action/")
    public CommentResponse action(@RequestParam("video_id") Integer videoId,
                                  @RequestParam("action_type") Integer actionType,
                                  @RequestParam(value = "comment_id", required = false) Integer commentId,
                                  @RequestParam(value = "comment_text", required = false) String commentText) {
        Comment comment = commentService.comment(videoId, actionType, commentId, commentText);
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setStatus_code(0);
        commentResponse.setStatus_msg("成功");
        commentResponse.setComment(comment);
        return commentResponse;
    }

    @GetMapping("/list/")
    public CommentListResponse list(@RequestParam("video_id") Integer videoId) throws JsonProcessingException {
        List<Comment> comments = commentService.commentList(videoId);
        CommentListResponse commentListResponse = new CommentListResponse();
        commentListResponse.setComment_list(comments);
        commentListResponse.setStatus_code(0);
        commentListResponse.setStatus_msg("成功");
        TestJsonUtil.showJson(comments);
        return commentListResponse;
    }
}
