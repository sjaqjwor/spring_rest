package com.kusob.api;

import com.kusob.domain.Comments.CommentService;
import com.kusob.domain.Comments.Request.CommentCreateRequestDto;
import com.kusob.domain.Comments.Request.CommentDeleteDto;
import com.kusob.domain.Comments.Request.CommentEdditRequestDto;
import com.kusob.domain.Comments.Response.CommentListResponse;
import com.kusob.domain.Posts.Response.PostListResponseDto;
import com.kusob.domain.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by seungki on 2017-07-20.
 */
@RestController
@Api(value = "Comments API", description = "coin.Comments Table API", basePath = "/api/comments")
@RequestMapping(value = "/api/comments")
public class CommentsController {

    @Autowired
    private CommentService commentService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "Comment 생성하기", notes = "Comment 생성하기",response = CommentListResponse.class)
    @RequestMapping(value = "/commentcreate", method = RequestMethod.POST)
    public CommentListResponse Create(HttpServletRequest httpServletRequest, @RequestBody CommentCreateRequestDto commentCreateRequestDto) {
        try {
            return commentService.create(httpServletRequest,commentCreateRequestDto);
        } catch (Exception e) {
            return new CommentListResponse("FAIL", null);
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "comment 삭제하기", notes = "comment 삭제하기",response = CommentListResponse.class)
    @RequestMapping(value = "/commentdelete", method = RequestMethod.DELETE)
    public CommentListResponse delete(HttpServletRequest httpServletRequest,@RequestBody CommentDeleteDto commentDeleteDto) {
        try {
            return commentService.delete(httpServletRequest,commentDeleteDto);
        } catch (Exception e) {
            return new CommentListResponse("FAIL",null);
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "Comment 수정", notes = "Comment 수정하기",response = CommentListResponse.class)
    @RequestMapping(value = "/commentedit", method = RequestMethod.PUT)
    public CommentListResponse eddit(HttpServletRequest httpServletRequest,@RequestBody CommentEdditRequestDto commentEdditRequestDto) {
        try {
            return commentService.edit(httpServletRequest,commentEdditRequestDto);
        } catch (Exception e) {
            return new CommentListResponse("FAIL",null);
        }
    }
}
