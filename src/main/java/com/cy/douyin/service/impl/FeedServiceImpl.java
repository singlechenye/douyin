package com.cy.douyin.service.impl;

import com.cy.douyin.mapper.FavoriteMapper;
import com.cy.douyin.mapper.RelationMapper;
import com.cy.douyin.mapper.VideoMapper;
import com.cy.douyin.model.domain.Video;
import com.cy.douyin.model.security.CustomUserDetails;
import com.cy.douyin.service.FeedService;
import com.cy.douyin.util.SecurityContextUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author 86147
 * create  14/6/2023 下午2:39
 */
@Service
public class FeedServiceImpl implements FeedService {

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private FavoriteMapper favoriteMapper;

    @Resource
    private RelationMapper relationMapper;

    @Override
    public List<Video> videoList() {

        List<Video> videos = videoMapper.videoList();
        try {
            Integer userId = SecurityContextUtil.getUserId();
            setVideoFavoriteFollow(videos, SecurityContextUtil.getUserId());
        }catch (RuntimeException e){
            videos.stream().forEach(item -> {
                item.set_favorite(false);
                item.getAuthor().set_follow(false);
            });
        }
        return videos;
    }
    @Override
    public List<Video> videoListById() {
        Integer userId = SecurityContextUtil.getUserId();
        List<Video> videos = videoMapper.videoListById(userId);
        return setVideoFavoriteFollow(videos, userId);
    }

    @Override
    public List<Video> videoListByFavorite() {
        Integer userId = SecurityContextUtil.getUserId();
        return filterFavoriteVideo(setVideoFavoriteFollow(videoMapper.videoList(),userId ),userId);
    }

    private List<Video> setVideoFavoriteFollow(List<Video> videos,int userId){
        videos.stream().forEach(video -> {
            Integer videoId = video.getId();
            Integer checkLike = favoriteMapper.checkLike(userId, videoId);
            if (checkLike!=null) {
                video.set_favorite(true);
            }else {
                video.set_favorite(false);
            }
            Integer checkFollow = relationMapper.checkFollow(userId, video.getAuthor().getId());
            if (checkFollow!=null) {
                video.getAuthor().set_follow(true);
            }else {
                video.getAuthor().set_follow(false);
            }
        });
        return videos;
    }

    private List<Video> filterFavoriteVideo(List<Video> videos,int userId){
        return videos.stream().filter(Video::is_favorite).collect(Collectors.toList());
    }
}
