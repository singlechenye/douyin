package com.cy.douyin.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer comment_id;

    /**
     * 
     */
    private Integer video_id;

    /**
     * 
     */
    @TableField(exist = false)
    private UserInfo user;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    @JsonFormat(pattern = "MM-dd")
    private Date create_date;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableLogic
    @JsonProperty(required = false)
    private Integer isDelete;
}