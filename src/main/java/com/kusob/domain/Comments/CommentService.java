package com.kusob.domain.Comments;

import com.kusob.config.JwtConfig.JwtService;
import com.kusob.domain.Comments.Request.CommentCreateRequestDto;
import com.kusob.domain.Comments.Request.CommentDeleteDto;
import com.kusob.domain.Comments.Request.CommentEdditRequestDto;
import com.kusob.domain.Comments.Response.CommentListResponse;
import com.kusob.domain.Comments.Response.CommentResponse;
import com.kusob.domain.ResponseDTO;
import com.kusob.mapper.CommentsMapper;
import com.kusob.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seungki on 2017-07-21.
 */
@Service
public class CommentService {

    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MemberMapper memberMapper;

    public CommentListResponse create(HttpServletRequest httpServletRequest, CommentCreateRequestDto commentCreateReuqestDto) throws Exception{
        try{
            String token = httpServletRequest.getHeader("Authorization");
            int myid= jwtService.idFromToken(token);
            CommentsCreate com = new CommentsCreate(commentCreateReuqestDto,myid);
            commentsMapper.addComment(com);
           List<Comments> list = commentsMapper.postComments(commentCreateReuqestDto.getPost_id());
           List<CommentResponse> comment_list = new ArrayList<>();
           for(Comments c : list){
                String nickname= memberMapper.selectById(c.getWritter_id()).getNickname();
                comment_list.add(new CommentResponse(c,nickname,c.getWritter_id()==myid));
            }

            return new CommentListResponse("SUCCESS",comment_list);
        }catch(Exception e){
            throw new Exception();
        }
    }

    public CommentListResponse edit(HttpServletRequest httpServletRequest, CommentEdditRequestDto commentEdditRequestDto)throws Exception{
        try{
                commentsMapper.edditComment(commentEdditRequestDto);
            List<Comments> list = commentsMapper.postComments(commentEdditRequestDto.getPost_id());
            List<CommentResponse> comment_list = new ArrayList<>();
            String token = httpServletRequest.getHeader("Authorization");
            int myid= jwtService.idFromToken(token);
            for(Comments c : list){
                String nickname= memberMapper.selectById(c.getWritter_id()).getNickname();
                comment_list.add(new CommentResponse(c,nickname,c.getWritter_id()==myid));
            }

            return new CommentListResponse("SUCCESS",comment_list);
        }catch (Exception e){
            throw new Exception();
        }
    }
    public CommentListResponse delete(HttpServletRequest httpServletRequest, CommentDeleteDto commentDeleteDto)throws Exception{
        try{
            commentsMapper.commentDelete(commentDeleteDto.getComment_id());
            List<Comments> list = commentsMapper.postComments(commentDeleteDto.getPost_id());
            List<CommentResponse> comment_list = new ArrayList<>();
            String token = httpServletRequest.getHeader("Authorization");
            int myid= jwtService.idFromToken(token);
            for(Comments c : list){
                String nickname= memberMapper.selectById(c.getWritter_id()).getNickname();
                comment_list.add(new CommentResponse(c,nickname,c.getWritter_id()==myid));
            }

            return new CommentListResponse("SUCCESS",comment_list);
        }catch (Exception e){
            throw new Exception();
        }
    }
}
