package com.cy.douyin.controller;

import com.cy.douyin.model.domain.Video;
import com.cy.douyin.model.response.FeedResponse;
import com.cy.douyin.model.response.Response;
import com.cy.douyin.service.FeedService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/feed")
public class FeedController {
    @Resource
    private FeedService feedService;
//    @RequestParam(value = "latest_time",required = false) Integer latestTime
//    这个好像是系统现在时间,好像拿这个没用
    @GetMapping
    public Response feed() throws IOException {
        List<Video> videos =feedService.videoList();
        return new FeedResponse(0, null, 0, videos);
    }
}
