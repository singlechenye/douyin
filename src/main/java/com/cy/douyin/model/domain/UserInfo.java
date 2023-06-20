package com.cy.douyin.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer follow_count;

    /**
     * 
     */
    private Integer follower_count;

    /**
     * 
     */
    @JsonProperty("is_follow")
    @TableField(exist = false)
    private boolean is_follow;

    /**
     * 
     */
    private String avatar;

    /**
     * 
     */
    private String background_image;

    /**
     * 
     */
    private String signature;

    /**
     * 
     */
    private Integer total_favorited;

    /**
     * 
     */
    private Integer work_count;

    /**
     * 
     */
    private Integer favorite_count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id.equals(userInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}