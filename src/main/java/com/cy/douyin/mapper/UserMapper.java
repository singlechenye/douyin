package com.cy.douyin.mapper;

import com.cy.douyin.model.domain.UserCore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86147
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-21 13:47:36
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<UserCore> {

}




