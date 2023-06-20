package com.cy.douyin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RelationMapper {

    @Insert("INSERT INTO user_follow_user VALUES (#{follow},#{followed})")
    public int follow(Integer follow ,Integer followed);


    @Delete("DELETE FROM user_follow_user WHERE follow_id= #{follow} AND followed_id= #{followed}")
    public int unFollow(Integer follow ,Integer followed);

    @Select("SELECT follow_id FROM user_follow_user WHERE follow_id=#{follow} AND followed_id=#{followed}")
    public Integer checkFollow(Integer follow ,Integer followed);

}

