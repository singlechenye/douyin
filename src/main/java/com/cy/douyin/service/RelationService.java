package com.cy.douyin.service;

import com.cy.douyin.model.domain.UserInfo;

import java.util.List;

public interface RelationService {

    public void follow(Integer toUserId,Integer actionType);

    List<UserInfo> followList();

    List<UserInfo> followerList();

    List<UserInfo> friendsList();
}
