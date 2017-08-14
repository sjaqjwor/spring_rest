package com.kusob.api;

import com.kusob.domain.Posts.*;
import com.kusob.domain.Posts.Request.PostCreateRequestDto;
import com.kusob.domain.Posts.Request.PostEdditReqeustDto;
import com.kusob.domain.Posts.Response.PostInfoResponseDto;
import com.kusob.domain.Posts.Response.PostListResponseDto;
import com.kusob.domain.Posts.Response.PostResponse;
import com.kusob.domain.ResponseDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by seungki on 2017-07-20.
 */
@RestController
@Api(value = "Post API", description = "coin.Post Table API", basePath = "/api/post")
@RequestMapping(value = "/api/post")
public class PostsController {

    @Autowired
    PostService postService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "Post 전체 리스트", notes = "Post 전체 리스트",response = PostListResponseDto.class)
    @RequestMapping(value = "/postlist", method = RequestMethod.GET)
    public PostListResponseDto list(HttpServletRequest httpServletRequest,@ApiParam(value = "기본값은 0 다음 10개를 보고 싶으면 1 그다음 열개는 2 이런식")
    @RequestParam(value = "size") int size) {
        try {
            return postService.list(httpServletRequest,size);
        } catch (Exception e) {
            return new PostListResponseDto("FAIL",null,null);
        }
    }

    @RequestMapping(value = "/guestpostlist", method = RequestMethod.GET)
    public PostListResponseDto guest_list(HttpServletRequest httpServletRequest,@ApiParam(value = "기본값은 0 다음 10개를 보고 싶으면 1 그다음 열개는 2 이런식")
    @RequestParam(value = "size") int size) {
        try {
            return postService.list(httpServletRequest,size);
        } catch (Exception e) {
            return new PostListResponseDto("FAIL",null,null);
        }
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "Post 생성하기", notes = "Post 생성하기",response = PostListResponseDto.class)
    @RequestMapping(value = "/postcreate", method = RequestMethod.POST)
    public PostListResponseDto Create(HttpServletRequest httpServletRequest, @RequestBody PostCreateRequestDto postCreateRequestDto) {
        try {
            return postService.create(httpServletRequest,postCreateRequestDto);
        } catch (Exception e) {
            return new PostListResponseDto("FAIL", null,null);
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "Post 삭제하기", notes = "Post 삭제하기",response = PostListResponseDto.class)
    @RequestMapping(value = "/postdelete", method = RequestMethod.DELETE)
    public PostListResponseDto delete(HttpServletRequest httpServletRequest,@RequestParam(value = "post_id")int id) {
        try {
            return postService.delete(httpServletRequest,id);
        } catch (Exception e) {
            return new PostListResponseDto("FAIL",null,null);
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "자세히보기", notes = "Post 자세히보기",response = PostInfoResponseDto.class)
    @RequestMapping(value = "/postinfo", method = RequestMethod.GET)
    public PostInfoResponseDto info(HttpServletRequest httpServletRequest,@RequestParam(value = "post_id")int id) {
        try {
            return postService.info(httpServletRequest,id);
        } catch (Exception e) {
            return new PostInfoResponseDto("FAIL");
        }
    }
    @RequestMapping(value = "/guestpostinfo", method = RequestMethod.GET)
    public PostInfoResponseDto guestinfo(HttpServletRequest httpServletRequest,@RequestParam(value = "post_id")int id) {
        try {
            return postService.info(httpServletRequest,id);
        } catch (Exception e) {
            return new PostInfoResponseDto("FAIL");
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "POST 수정", notes = "Post 수정하기",response = PostInfoResponseDto.class)
    @RequestMapping(value = "/posteddit", method = RequestMethod.PUT)
    public PostInfoResponseDto eddit(HttpServletRequest httpServletRequest,@RequestBody PostEdditReqeustDto postEdditReqeustDto) {
        try {
            return postService.eddit(httpServletRequest,postEdditReqeustDto);
        } catch (Exception e) {
            return new PostInfoResponseDto("FAIL");
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "POST 좋아요", notes = "Post 좋아요",response = ResponseDTO.class)
    @RequestMapping(value = "/postlike", method = RequestMethod.POST)
    public ResponseDTO like(HttpServletRequest httpServletRequest, @RequestParam(value = "post_id")int post_id) {
        try {
            return postService.like(httpServletRequest,post_id);
        } catch (Exception e) {
            return new ResponseDTO("FAIL");
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "POST 좋아요 취소", notes = "Post 좋아요 취소 ",response = ResponseDTO.class)
    @RequestMapping(value = "/postlikedelete", method = RequestMethod.DELETE)
    public ResponseDTO likeDelete(HttpServletRequest httpServletRequest, @RequestParam(value = "post_id")int post_id) {
        try {
            return postService.likeDelete(httpServletRequest,post_id);
        } catch (Exception e) {
            return new ResponseDTO("FAIL");
        }
    }
}
