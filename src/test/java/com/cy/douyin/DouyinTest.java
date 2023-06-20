package com.cy.douyin;

import com.cy.douyin.model.domain.Comment;
import com.cy.douyin.service.CommentService;
import com.cy.douyin.service.FeedService;
import com.cy.douyin.service.PublishService;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.util.StreamUtils;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;

@SpringBootTest
class DouyinTest {

    @Autowired
    private PublishService publishService;

    @Autowired
    private FeedService feedService;


    @Autowired
    private CommentService commentService;


    @Test
     void testFile() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:jpg/test.jpg");
        InputStream inputStream = resource.getInputStream();
        // 将InputStream对象转换为字节数组
        byte[] bytes = StreamUtils.copyToByteArray(inputStream);
        publishService.uploadVideo(bytes,"test","jpg");
    }

    @Test
    void  downloadFile() throws QiniuException {
        DownloadUrl url = new DownloadUrl("rw3ap3jyt.bkt.clouddn.com",false,"chenye/test.jpg");
        String s = url.buildURL();
        System.out.println(s);
    }

    @Test
    void  videoList() throws QiniuException {
        feedService.videoList().stream().forEach(item -> System.out.println(item));
    }

    @Test
    void  commentList() throws QiniuException {
        commentService.commentList(1012).stream().forEach(item -> System.out.println(item));
    }
}
