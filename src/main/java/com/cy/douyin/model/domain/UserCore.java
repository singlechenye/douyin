package com.cy.douyin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user_core")
@Data
public class UserCore implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Integer user_status;

    /**
     * 
     */
    private Integer is_delete;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private Integer user_role;

    public UserCore() {
    }

    public UserCore(Integer id, String username, String password, Integer user_status, Integer is_delete, Integer user_role) {
        this.id = id;
        this.username = username;
        this.password = password;

        this.user_status = user_status;

        this.is_delete = is_delete;
        this.user_role = user_role;
    }
}