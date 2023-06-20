package com.cy.douyin.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
public class Video {
    /**
     * 视频作者信息
     */
    private UserInfo author;
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
     * true-已点赞，false-未点赞
     */
    @JsonProperty("is_favorite")
    @TableField(exist = false)
    private boolean is_favorite;
    /**
     * 视频播放地址
     */
    private String play_url;
    /**
     * 视频标题
     */
    private String title;

}