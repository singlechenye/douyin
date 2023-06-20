package com.cy.douyin.service;

import com.cy.douyin.model.domain.Video;

import java.util.List;

public interface FeedService {
    List<Video> videoList();

    List<Video> videoListById();

    List<Video> videoListByFavorite();
}
