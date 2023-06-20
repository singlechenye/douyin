package com.cy.douyin.controller;

import com.cy.douyin.model.domain.Video;
import com.cy.douyin.model.response.FeedResponse;
import com.cy.douyin.model.response.Response;
import com.cy.douyin.service.FavoriteService;
import com.cy.douyin.service.FeedService;
import com.cy.douyin.util.ResponseUtil;
import com.cy.douyin.util.TestJsonUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Resource
    private FavoriteService favoriteService;

    @Resource
    private FeedService feedService;

    /**
     * 1是点赞,2是取消点赞
     * @param action_type
     * @param videoId
     * @return
     */
    @PostMapping("/action/")
    public Response action(@RequestParam Integer action_type,
                         @RequestParam("video_id") Integer videoId) {
        favoriteService.setLike(action_type, videoId);
        return ResponseUtil.success();
    }

    @GetMapping("/list/")
    public FeedResponse list(@RequestParam("user_id") Integer userId) {
        List<Video> videos = feedService.videoListByFavorite();
        return new FeedResponse(0, null, 0, videos);
    }
}
