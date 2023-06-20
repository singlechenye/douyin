package com.cy.douyin.service.impl;

import com.cy.douyin.constant.ResponseConstants;
import com.cy.douyin.mapper.FavoriteMapper;
import com.cy.douyin.mapper.UserInfoMapper;
import com.cy.douyin.mapper.VideoMapper;
import com.cy.douyin.service.FavoriteService;
import com.cy.douyin.util.ExceptionUtil;
import com.cy.douyin.util.SecurityContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteMapper favoriteMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private VideoMapper videoMapper;

    @Override
    @Transactional
    public void setLike(Integer actionType, Integer videoId) {
        Integer userId = SecurityContextUtil.getUserId();
        if (actionType==1){
            int i = favoriteMapper.addLike(userId, videoId);
            if (i==0) ExceptionUtil.throwAppErr(ResponseConstants.FAVORITE_ERROR);
            userInfoMapper.increFavorite(userId);
            videoMapper.increFavorite(videoId);
            Integer authorId = videoMapper.getAuthorId(videoId);
            userInfoMapper.increFavorited(authorId);
        }else if(actionType==2){
            favoriteMapper.removeLike(userId,videoId);
            userInfoMapper.decreFavorite(userId);
            videoMapper.decreFavorite(videoId);
            Integer authorId = videoMapper.getAuthorId(videoId);
            userInfoMapper.decreFavorited(authorId);
        }
    }
}
