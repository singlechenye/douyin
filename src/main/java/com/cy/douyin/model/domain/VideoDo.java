package com.cy.douyin.model.domain;

import lombok.Data;

/**
 * @author 86147
 * create  14/6/2023 下午4:39
 */
@Data
public class VideoDo {

    private Integer author_id;
    /**
     * 视频的评论总数
     */
    private long comment_count;
    /**
     * 视频封面地址
     */
    private String cover_url;
    /**
     * 视频的点赞总数
     */
    private long favorite_count;
    /**
     * 视频唯一标识
     */
    private Integer id;

    /**
     * 视频播放地址
     */
    private String play_url;
    /**
     * 视频标题
     */
    private String title;
}
