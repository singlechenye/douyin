package com.cy.douyin.model.response;

import com.cy.douyin.model.domain.UserInfo;
import lombok.Data;

import java.util.List;

/**
 * @author 86147
 * create  19/6/2023 上午11:04
 */
@Data
public class UserListResponse extends Response{

    private List<UserInfo> user_list;
}
