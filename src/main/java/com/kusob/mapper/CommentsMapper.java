package com.kusob.mapper;

import com.kusob.domain.Comments.Comments;
import com.kusob.domain.Comments.CommentsCreate;
import com.kusob.domain.Comments.Request.CommentEdditRequestDto;
import com.kusob.domain.Comments.Response.CommentResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by seungki on 2017-07-18.
 */
@Mapper
public interface CommentsMapper {
    List<Comments> postComments(int post_id);
    void addComment(CommentsCreate commentsCreate);
    void edditComment(CommentEdditRequestDto commentEdditRequestDto);
    void commentDelete(int id);

}
