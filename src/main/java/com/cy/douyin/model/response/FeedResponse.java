package com.cy.douyin.model.response;

import com.cy.douyin.model.domain.Video;
import lombok.Data;

import java.util.List;

/**
 * @author 86147
 * create  14/6/2023 下午2:45
 */
@Data
public class FeedResponse extends Response{

    private Integer next_time;

    private List<Video> video_list;

    public FeedResponse(int status_code, String status_msg) {
        super(status_code, status_msg);
    }

    public FeedResponse(int status_code, String status_msg, Integer next_time, List<Video> video_Vo_list) {
        super(status_code, status_msg);
        this.next_time = next_time;
        this.video_list = video_Vo_list;
    }
}
