package com.cy.douyin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author 86147
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-21 13:47:36
* @Entity generator.domain.User
*/
@Mapper
public interface FavoriteMapper {

    @Insert("INSERT INTO user_favorite_video VALUES (#{userId},#{videoId});")
    public int addLike(Integer userId,Integer videoId);

    @Insert("DELETE FROM user_favorite_video WHERE user_id=#{userId} AND video_id =#{videoId}")
    public int removeLike(Integer userId,Integer videoId);

    @Select("SELECT user_id FROM user_favorite_video WHERE user_id=#{userId} AND video_id =#{videoId}")
    public Integer checkLike(Integer userId,Integer videoId);

}




