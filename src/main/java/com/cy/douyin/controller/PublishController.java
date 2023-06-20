package com.cy.douyin.controller;

import com.cy.douyin.model.domain.Video;
import com.cy.douyin.model.response.FeedResponse;
import com.cy.douyin.model.response.Response;
import com.cy.douyin.service.FeedService;
import com.cy.douyin.service.PublishService;
import com.cy.douyin.util.ResponseUtil;
import com.cy.douyin.util.TestJsonUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

import static com.google.common.io.Files.getFileExtension;


@RestController
@RequestMapping("/publish")
public class PublishController {
    @Resource
    private PublishService publishService;

    @Resource
    private FeedService feedService;

    @PostMapping("/action/")
    public Response action(@RequestPart("data") MultipartFile data,
                         @RequestParam("title") String title) throws IOException {
        byte[] bytes = data.getBytes();
        String type = getFileExtension(Objects.requireNonNull(data.getOriginalFilename()));
        publishService.uploadVideo(bytes,title,type);
        return ResponseUtil.success();
    }

    @GetMapping("/list/")
    public FeedResponse list() throws IOException {
        List<Video> videos =feedService.videoListById();
        return new FeedResponse(0, null, 0, videos);
    }
}
