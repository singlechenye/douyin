package com.cy.douyin.mapper;

import com.cy.douyin.model.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86147
* @description 针对表【message】的数据库操作Mapper
* @createDate 2023-06-19 14:53:19
* @Entity com.cy.douyin.model.domain.Message
*/
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}




