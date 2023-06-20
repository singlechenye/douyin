package com.cy.douyin.mapper;

import com.cy.douyin.model.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.douyin.model.domain.CommentDo;
import com.cy.douyin.model.domain.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author 86147
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2023-06-17 16:47:40
* @Entity com.cy.douyin.model.domain.Comment
*/
@Mapper
public interface CommentMapper{

    List<Comment> commentList(@Param("videoId") Integer videoId);

    Comment getByCommentId(@Param("commentId") Integer commentId);

    Integer comment(CommentDo commentDo);

    @Update("UPDATE comment SET is_delete=1 WHERE comment_id=#{commentId}")
    Integer deleteComment(Integer commentId);

}




