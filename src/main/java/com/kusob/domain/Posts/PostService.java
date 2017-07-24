package com.kusob.domain.Posts;

import com.kusob.config.JwtConfig.JwtService;
import com.kusob.domain.Comments.Comments;
import com.kusob.domain.Comments.Response.CommentResponse;
import com.kusob.domain.PostLikes.PostLikesCreate;
import com.kusob.domain.Posts.Request.PostCreateRequestDto;
import com.kusob.domain.Posts.Request.PostEdditReqeustDto;
import com.kusob.domain.Posts.Response.PostInfoResponseDto;
import com.kusob.domain.Posts.Response.PostListResponseDto;
import com.kusob.domain.Posts.Response.PostResponse;
import com.kusob.domain.ResponseDTO;
import com.kusob.mapper.CommentsMapper;
import com.kusob.mapper.MemberMapper;
import com.kusob.mapper.PostLikesMapper;
import com.kusob.mapper.PostsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seungki on 2017-07-20.
 */
@Service
public class PostService {

    @Autowired
    private PostsMapper postsMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private PostLikesMapper postLikesMapper;

    public PostListResponseDto list(HttpServletRequest httpServletRequest,int count_size) throws Exception{
        try{
                List<Posts> top_three = postsMapper.allList_3();
                int num=0;
                if(count_size==0){
                    num=10;
                }else{
                    num=10*(count_size+1);
                }
            PostTop postTop = new PostTop(top_three.get(0).getPost_id(),top_three.get(1).getPost_id(),top_three.get(2).getPost_id(),num);
                List<Posts> list = postsMapper.allList(postTop);
            System.out.println(list.size()+"승기");
                return new PostListResponseDto("SUCCESS",list ,postsMapper.allList_3());
        }catch (Exception e){
            throw new Exception();
        }
    }

    public PostListResponseDto create(HttpServletRequest httpServletRequest,PostCreateRequestDto postCreateRequestDto) throws Exception{
        try{
            String token = httpServletRequest.getHeader("Authorization");
            int writter_id = jwtService.idFromToken(token);
            postsMapper.createPost(new PostCreateDto(writter_id,postCreateRequestDto.getTitle(),postCreateRequestDto.getContents()));
            return list(httpServletRequest,0);
        }catch(Exception e){
            throw new Exception();
        }
    }
    public PostListResponseDto delete(HttpServletRequest httpServletRequest,int id)throws Exception{
        try{
            postsMapper.deletePosts(id);
            return list(httpServletRequest,0);
        }catch(Exception e){
            throw new Exception();
        }
    }
    public PostInfoResponseDto info(HttpServletRequest httpServletRequest, int id) throws Exception{
        try{
            String token= httpServletRequest.getHeader("Authorization");
            int myid= jwtService.idFromToken(token);
            Posts post = postsMapper.postsInfo(id);
            String nickName = memberMapper.selectById(post.getWritter_id()).getNickname();
            List<Comments> list = commentsMapper.postComments(id);
            System.out.println(list.size()+"승기");
            List<CommentResponse> comment_list = new ArrayList<>();
            for(Comments c : list){
                String nickname= memberMapper.selectById(c.getWritter_id()).getNickname();
                comment_list.add(new CommentResponse(c,nickname,c.getWritter_id()==myid));
            }
            return new PostInfoResponseDto("SUCCESS",post,nickName,myid==post.getWritter_id(),comment_list);
        }catch (Exception e){
            throw new Exception();
        }
    }

    public PostInfoResponseDto eddit(HttpServletRequest httpServletRequest, PostEdditReqeustDto postEdditReqeustDto)throws Exception{
        try{
            postsMapper.eddPost(postEdditReqeustDto);
            return info(httpServletRequest,postEdditReqeustDto.getPost_id());
        }catch (Exception e){
            throw  new Exception();
        }
    }
    public ResponseDTO like(HttpServletRequest httpServletRequest, int id)throws Exception{
        try{
            Posts post = postsMapper.postsInfo(id);
            post.setLikes_count(post.getLikes_count()+1);
            String token=httpServletRequest.getHeader("Authorization");
            postsMapper.postlike(post);
            int myid= jwtService.idFromToken(token);
            postLikesMapper.insertLikes(new PostLikesCreate(id,myid));
            return new ResponseDTO("SUCCESS");
        }catch (Exception e){
            throw  new Exception();
        }
    }
}
