package com.kusob.api;

import com.kusob.domain.Friends.FriendsService;
import com.kusob.domain.ResponseDTO;
import com.kusob.domain.member.Member;
import com.kusob.domain.member.MemberDTO;
import com.kusob.domain.member.MemberFreindDto;
import com.kusob.domain.member.MemberService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by seungki on 2017-07-18.
 */
@Slf4j
@Api(value = "친구추가 API", description = "coin.Friend Table API", basePath = "/api/friend")
@RestController
@RequestMapping("/api/friend")
public class FriendController {

    @Autowired
    MemberService memberService;

    @Autowired
    FriendsService friendsService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "친구추가", notes = "친구추가 api")
    @RequestMapping(value = "/meet", method = RequestMethod.POST)
    public ResponseDTO join(HttpServletRequest httpServletRequest, @RequestParam(value = "Friendid")int id) {
        try{
            return friendsService.meetFriend(id,httpServletRequest);
        }catch (Exception e){
            return new ResponseDTO("fail");
        }

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "친구검색", notes = "친구검색 api")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Map search(HttpServletRequest httpServletRequest, @RequestParam(value = "FriendNickName")String nickName) {
        try {
            return memberService.searchFriend(nickName,httpServletRequest);
        }catch (Exception e){
            return memberService.searchFriend(nickName,httpServletRequest);
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "친구수락", notes = "친구수락 api")
    @RequestMapping(value = "/agree", method = RequestMethod.PUT)
    public ResponseDTO agree(HttpServletRequest httpServletRequest, @ApiParam(value = "친구요청 보낸 사람의 id 값")@RequestParam(value = "Friendid")int  id) {
        try {
            return friendsService.agreeFriend(id,httpServletRequest);
        }catch (Exception e){
            return new ResponseDTO("FAIL");
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "내가 보낸 친구 요청 상태 확인", notes = "친구요청 확인 api")
    @RequestMapping(value = "/confirmmyside", method = RequestMethod.GET)
    public Map confirmMySide(HttpServletRequest httpServletRequest) {
        try {
            return friendsService.confirmMySide(httpServletRequest);
        }catch (Exception e){
            Map<Integer,String> h = new HashMap<>();
            h.put(0,"FAIL");
            return h;
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "내가 받은 친구 요청 상태 확인", notes = "친구요청 확인 api")
    @RequestMapping(value = "/confirmcheck", method = RequestMethod.GET)
    public Map confrimCheck(HttpServletRequest httpServletRequest) {
        try {
            return friendsService.confirmCheck(httpServletRequest);
        }catch (Exception e){
            Map<Integer,String> h = new HashMap<>();
            h.put(0,"FAIL");
            return h;
        }
    }
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "내가 받은 친구 요청 상태 확인", notes = "친구요청 확인 api")
    @RequestMapping(value = "/myfriend", method = RequestMethod.GET)
    public List<MemberFreindDto> myFriend(HttpServletRequest httpServletRequest) {
        try {
            return friendsService.confirmCheck(httpServletRequest);
        }catch (Exception e){
            Map<Integer,String> h = new HashMap<>();
            h.put(0,"FAIL");
            return h;
        }
    }
    */


}
