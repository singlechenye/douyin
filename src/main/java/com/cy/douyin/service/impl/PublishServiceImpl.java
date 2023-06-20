package com.cy.douyin.service.impl;

import com.cy.douyin.mapper.UserInfoMapper;
import com.cy.douyin.mapper.VideoMapper;
import com.cy.douyin.model.domain.VideoDo;
import com.cy.douyin.service.PublishService;
import com.cy.douyin.thread.CustomThreadPool;
import com.cy.douyin.util.QiniuUtil;
import com.cy.douyin.util.SecurityContextUtil;
import com.cy.douyin.util.VideoUtil;
import com.qiniu.common.QiniuException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.cy.douyin.constant.QiniuyunConstants.*;

/**
 * @author 86147
 * create  14/6/2023 上午11:03
 */
@Transactional
@Service
public class PublishServiceImpl implements PublishService {

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * btyes本来就是商视频流
     * @param bytes
     * @param title
     * @param type
     * @return
     */
    @Override
    public void uploadVideo(byte[] bytes, String title,String type) throws QiniuException {
        //获取当前用户名
        Integer userId = SecurityContextUtil.getUserId();
        String username = SecurityContextUtil.getUsername();
        //获取封面
        String videoTitle =username+"/video:"+System.currentTimeMillis()+"."+type;
        String pictureTitle =username+"/pict:"+System.currentTimeMillis()+".jpeg";
        //将视频信息存入数据库
        VideoDo videoDO = new VideoDo();
        videoDO.setComment_count(0L);
        videoDO.setCover_url(URL_PREFIX+pictureTitle);
        videoDO.setFavorite_count(0L);
        videoDO.setPlay_url(URL_PREFIX+videoTitle);
        videoDO.setTitle(title);
        videoDO.setAuthor_id(Math.toIntExact(userId));
        videoMapper.saveVideo(videoDO);
        //将用户视频数加一
        userInfoMapper.increVideo(userId);
        CustomThreadPool.execute(() -> {
            byte[] pictByte = VideoUtil.extractCoverImage(bytes);
            try {
                QiniuUtil.uploadBytes(bytes,videoTitle);
                QiniuUtil.uploadBytes(pictByte,pictureTitle);
            } catch (QiniuException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
