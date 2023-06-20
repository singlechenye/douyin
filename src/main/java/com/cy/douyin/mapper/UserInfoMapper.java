package com.cy.douyin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.douyin.model.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author 86147
* @description 针对表【user_info】的数据库操作Mapper
* @createDate 2023-06-13 15:00:24
* @Entity generator.domain.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Update("UPDATE user_info SET favorite_count = user_info.favorite_count + 1 WHERE id = #{userId}")
    public Integer increFavorite(@Param("userId") int userId);

    @Update("UPDATE user_info SET total_favorited = user_info.total_favorited + 1 WHERE id = #{userId}")
    public Integer increFavorited(@Param("userId") int userId);

    @Update("UPDATE user_info SET favorite_count = user_info.favorite_count - 1 WHERE id = #{userId}")
    public Integer decreFavorite(@Param("userId") int userId);

    @Update("UPDATE user_info SET total_favorited = user_info.total_favorited - 1 WHERE id = #{userId}")
    public Integer decreFavorited(@Param("userId") int userId);

    @Update("UPDATE user_info SET work_count = user_info.work_count + 1 WHERE id = #{userId}")
    public Integer increVideo(@Param("userId") int userId);

    @Update("UPDATE user_info SET follower_count = user_info.favorite_count + 1 WHERE id = #{userId}")
    public Integer increFollowed(@Param("userId") int userId);

    @Update("UPDATE user_info SET follower_count = user_info.follower_count - 1 WHERE id = #{userId}")
    public Integer decreFollowed(@Param("userId") int userId);

    @Update("UPDATE user_info SET follow_count = user_info.follow_count + 1 WHERE id = #{userId}")
    public Integer increFollow(@Param("userId") int userId);

    @Update("UPDATE user_info SET follow_count = user_info.follow_count - 1 WHERE id = #{userId}")
    public Integer decreFollow(@Param("userId") int userId);

    @Select("SELECT user_info.* FROM user_info INNER JOIN user_follow_user ON user_info.id = user_follow_user.followed_id WHERE user_follow_user.follow_id = #{userId}")
    public List<UserInfo> allFollowed(@Param("userId") int userId);

    @Select("SELECT user_info.* FROM user_info INNER JOIN user_follow_user ON user_info.id = user_follow_user.follow_id WHERE user_follow_user.followed_id = #{userId}")
    public List<UserInfo> allFollow(@Param("userId") int userId);



}




