package com.cy.douyin.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class CommentDo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer commentId;

    /**
     * 
     */
    private Integer videoId;

    /**
     * 
     */
    private Integer userInfoId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableLogic
    private Integer isDelete;
}