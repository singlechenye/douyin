package com.cy.douyin.controller;

import com.cy.douyin.model.domain.Comment;
import com.cy.douyin.model.domain.UserInfo;
import com.cy.douyin.model.response.CommentListResponse;
import com.cy.douyin.model.response.CommentResponse;
import com.cy.douyin.model.response.Response;
import com.cy.douyin.model.response.UserListResponse;
import com.cy.douyin.service.CommentService;
import com.cy.douyin.service.RelationService;
import com.cy.douyin.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86147
 * create  17/6/2023 下午4:42
 */
@RestController
@RequestMapping("/relation")
public class RelationController {

    @Resource
    private RelationService relationService;

    /**
     * token
     * string
     * 用户鉴权token
     * 必需
     * to_user_id
     * string
     * 对方用户id
     * 必需
     * action_type
     * string
     * 必需
     * 1关注
     * 2取消关注
     * @return
     */
    @PostMapping("/action")
    public Response action(@RequestParam("to_user_id") Integer toUserId,
                           @RequestParam("action_type") Integer actionType) {
        relationService.follow(toUserId,actionType);
        return ResponseUtil.success();
    }

    @GetMapping("/follow/list")
    public Response list() {
        List<UserInfo> userInfos = relationService.followList();
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUser_list(userInfos);
        return userListResponse;
    }

    @GetMapping("/follower/list")
    public Response lister() {
        List<UserInfo> userInfos = relationService.followerList();
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUser_list(userInfos);
        return userListResponse;
    }

    @GetMapping("/friend/list")
    public Response friends() {
        List<UserInfo> userInfos = relationService.friendsList();
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUser_list(userInfos);
        return userListResponse;
    }
}
