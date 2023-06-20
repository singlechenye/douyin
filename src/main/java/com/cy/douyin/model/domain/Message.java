package com.cy.douyin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer to_user_id;

    /**
     * 
     */
    private Integer from_user_id;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    @JsonIgnore
    @TableField(value = "create_time")
    private Date createTime;




    @TableField(exist = false)
    private Integer create_time;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}