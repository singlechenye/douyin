package com.cy.douyin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.douyin.constant.ResponseConstants;
import com.cy.douyin.mapper.CommentMapper;
import com.cy.douyin.mapper.VideoMapper;
import com.cy.douyin.model.domain.Comment;
import com.cy.douyin.model.domain.CommentDo;
import com.cy.douyin.model.domain.Video;
import com.cy.douyin.service.CommentService;
import com.cy.douyin.util.ExceptionUtil;
import com.cy.douyin.util.SecurityContextUtil;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86147
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-06-17 16:47:41
*/
@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private VideoMapper videoMapper;

    @Override
    public List<Comment> commentList(Integer videoId) {
        List<Comment> comments = commentMapper.commentList(videoId);
        return comments;
    }



    @Override
    public Comment comment(Integer videoId, Integer actionType, Integer commentId, String content) {
        Comment comment=null;
        if (actionType==1) {
            //发布评论
            Integer userId = SecurityContextUtil.getUserId();
            CommentDo commentDo = new CommentDo();
            commentDo.setVideoId(videoId);
            commentDo.setContent(content);
            commentDo.setUserInfoId(userId);
            Integer insert = commentMapper.comment(commentDo);
            if (insert!=1) ExceptionUtil.throwAppErr(ResponseConstants.FAIL);
            Integer add = videoMapper.addComment(videoId);
            if (add!=1)  ExceptionUtil.throwAppErr(ResponseConstants.FAIL);
            Integer key = commentDo.getCommentId();
            comment = commentMapper.getByCommentId(key);
        }else {
            //删除评论
            Integer del = commentMapper.deleteComment(commentId);
            if (del!=1) ExceptionUtil.throwAppErr(ResponseConstants.FAIL);
            Integer decr = videoMapper.removeComment(videoId);
            if (decr!=1)  ExceptionUtil.throwAppErr(ResponseConstants.FAIL);
        }
        return comment;
    }

}




