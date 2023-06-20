package com.cy.douyin.service.impl;

import com.cy.douyin.mapper.RelationMapper;
import com.cy.douyin.mapper.UserInfoMapper;
import com.cy.douyin.model.domain.UserInfo;
import com.cy.douyin.model.domain.Video;
import com.cy.douyin.service.RelationService;
import com.cy.douyin.util.ExceptionUtil;
import com.cy.douyin.util.SecurityContextUtil;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86147
 * create  19/6/2023 上午10:11
 */
@Transactional
@Service
public class RelationServiceImpl implements RelationService {

    @Resource
    private RelationMapper relationMapper;

    @Resource
    private UserInfoMapper userInfoMapper;


    @Override
    public void follow(Integer toUserId, Integer actionType) {
        Integer userId = SecurityContextUtil.getUserId();
        if (actionType==1){
            int follow = relationMapper.follow(userId, toUserId);
            Integer incr1 = userInfoMapper.increFollow(userId);
            Integer incr2 = userInfoMapper.increFollowed(toUserId);
            if (follow == 0 && (incr1 == null || incr1 == 0) && (incr2 == null || incr2 == 0)) {
                ExceptionUtil.throwAppErr("关注异常");
            }

        }else if (actionType==2){
            int i = relationMapper.unFollow(userId, toUserId);
            Integer integer = userInfoMapper.decreFollow(userId);
            Integer integer1 = userInfoMapper.decreFollowed(toUserId);
            if (i == 0 && (integer == null || integer == 0) && (integer1 == null || integer1 == 0)) {
                ExceptionUtil.throwAppErr("取消关注异常");
            }
        }
    }

    @Override
    public List<UserInfo> followList() {
        Integer userId = SecurityContextUtil.getUserId();
        List<UserInfo> userInfos = userInfoMapper.allFollowed(userId);
        return setUserFollow(userInfos,userId);
    }

    @Override
    public List<UserInfo> followerList() {
        Integer userId = SecurityContextUtil.getUserId();
        List<UserInfo> userInfos = userInfoMapper.allFollow(userId);
        return setUserFollow(userInfos,userId);
    }

    @Override
    public List<UserInfo> friendsList() {
        List<UserInfo> followerList = followerList();
        List<UserInfo> followList = followList();
        followList.retainAll(followerList);
        return followList;
    }

    private List<UserInfo> setUserFollow(List<UserInfo> userInfos, int userId){
        userInfos.forEach(userInfo -> {
            Integer checkFollow = relationMapper.checkFollow(userId, userInfo.getId());
            if (checkFollow!=null) {
                userInfo.set_follow(true);
            }else {
                userInfo.set_follow(false);
            }
        });
        return userInfos;
    }
}
