package com.cy.douyin.mapper;


import com.cy.douyin.model.domain.VideoDo;
import com.cy.douyin.model.domain.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author 86147
* @description 针对表【video(视频表)】的数据库操作Mapper
* @createDate 2023-06-14 15:08:17
* @Entity generator.domain.Video
*/
@Mapper
public interface VideoMapper {
    @Results(id = "videoMap1", value = {
            // 将查询结果映射为 User 对象，并关联到 Video 对象的 user 属性中
            @Result(column = "author_id", property = "author",
                    one = @One(select = "com.cy.douyin.mapper.UserInfoMapper.selectById"))
    })
    @Select("SELECT * FROM `video` ORDER BY id desc limit 30")
    List<Video> videoList();


    @Results(id = "videoMap2", value = {
            // 将查询结果映射为 User 对象，并关联到 Video 对象的 user 属性中
            @Result(column = "author_id", property = "author",
                    one = @One(select = "com.cy.douyin.mapper.UserInfoMapper.selectById"))
    })
    @Select("SELECT * FROM `video` WHERE author_id=#{authorId} ORDER BY id desc ")
    List<Video> videoListById(@Param("authorId")Integer authorId);

    @Results(id = "videoMap3", value = {
            // 将查询结果映射为 User 对象，并关联到 Video 对象的 user 属性中
            @Result(column = "author_id", property = "author",
                    one = @One(select = "com.cy.douyin.mapper.UserInfoMapper.selectById"))
    })
    @Select("SELECT v.* FROM video v JOIN user_favorite_video uf ON v.id = uf.video_id WHERE uf.user_id = 1 ORDER BY v.id DESC")
    List<Video> videoListByFavorite(@Param("authorId")Integer authorId);

    @Insert("INSERT INTO video(author_id, comment_count, cover_url, favorite_count, id, play_url, title) VALUES(#{author_id}, #{comment_count}, #{cover_url}, #{favorite_count}, #{id}, #{play_url}, #{title})")
    int saveVideo(VideoDo videoDO);


    @Update("UPDATE video SET favorite_count = video.favorite_count + 1 WHERE id = #{videoId}")
    public Integer increFavorite(@Param("videoId") int videoId);


    @Update("UPDATE video SET favorite_count = video.favorite_count - 1 WHERE id = #{videoId}")
    public Integer decreFavorite(@Param("videoId") int videoId);

    @Select("SELECT author_id FROM video WHERE id = #{videoId}")
    public Integer getAuthorId(Integer videoId);

    @Update("UPDATE video SET comment_count = video.comment_count + 1 WHERE id = #{videoId}")
    public Integer addComment(Integer videoId);

    @Update("UPDATE video SET comment_count = video.comment_count - 1 WHERE id = #{videoId}")
    public Integer removeComment(Integer videoId);

}




