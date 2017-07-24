package com.kusob.api;

import com.kusob.domain.Friends.FriendsService;
import com.kusob.domain.member.ListMemberFriendResponseDto;
import com.kusob.domain.wallet.ListWalletResponse;
import com.kusob.domain.ResponseDTO;
import com.kusob.domain.member.MemberService;
import com.kusob.domain.wallet.WalletService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    WalletService walletService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "친구추가", notes = "친구추가 api",response = ResponseDTO.class)
    @RequestMapping(value = "/meet", method = RequestMethod.POST)
    public ResponseDTO join(HttpServletRequest httpServletRequest, @RequestParam(value = "Friendid")int id) {
        try{
            return friendsService.meetFriend(id,httpServletRequest);
        }catch (Exception e){
            return new ResponseDTO("FAIL");
        }

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "친구검색", notes = "친구검색 api",response = ListMemberFriendResponseDto.class)
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ListMemberFriendResponseDto search(HttpServletRequest httpServletRequest, @RequestParam(value = "FriendNickName")String nickName) {
        try {
            return memberService.searchFriend(nickName,httpServletRequest);
        }catch (Exception e){
            return new ListMemberFriendResponseDto("FAIL",null);
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "친구수락", notes = "친구수락 api",response = ResponseDTO.class)
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
    @ApiOperation(value = "내가 보낸 친구 요청 상태 확인", notes = "친구요청 확인 api",response = ListMemberFriendResponseDto.class)
    @RequestMapping(value = "/confirmmyside", method = RequestMethod.GET)
    public ListMemberFriendResponseDto confirmMySide(HttpServletRequest httpServletRequest) {
        try {
            return friendsService.confirmMySide(httpServletRequest);
        }catch (Exception e){
            return new ListMemberFriendResponseDto("FAIL",null);
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "내가 받은 친구 요청 상태 확인", notes = "친구요청 확인 api",response = ListMemberFriendResponseDto.class)
    @RequestMapping(value = "/confirmcheck", method = RequestMethod.GET)
    public ListMemberFriendResponseDto confrimCheck(HttpServletRequest httpServletRequest) {
        try {
            return friendsService.confirmCheck(httpServletRequest);
        }catch (Exception e){
            return new ListMemberFriendResponseDto("FAIL",null);
        }
    }
   @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "친구 지갑 보기", notes = "친구 지갑 api",response = ListWalletResponse.class)
    @RequestMapping(value = "/lookfriendwallet", method = RequestMethod.GET)
    public ListWalletResponse lookFriendWallet(@ApiParam(value = "보고자 하는 친구의 아이디값")@RequestParam(value = "friendId")int id, HttpServletRequest httpServletRequest) {
        try {
            return walletService.getWalletList(id);
        }catch (Exception e){
            return new ListWalletResponse("FAIL",null);
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "내 친구보기", notes = "내 친구리스트",response = ListMemberFriendResponseDto.class)
    @RequestMapping(value = "/myfriend", method = RequestMethod.GET)
    public ListMemberFriendResponseDto lookFriendWallet(HttpServletRequest httpServletRequest) {
        try {
            return friendsService.myfriend(httpServletRequest);
        }catch (Exception e){
            return new ListMemberFriendResponseDto("FAIL",null);
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "친구삭제", notes = "친구삭제",response = ResponseDTO.class)
    @RequestMapping(value = "/deletefriend", method = RequestMethod.DELETE)
    public ResponseDTO delete(HttpServletRequest httpServletRequest,@RequestParam(value = "friendId")int id) {
        try {
            return friendsService.delete(httpServletRequest,id);
        }catch (Exception e){
            return new ResponseDTO("FAIL");
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "친구 거절", notes = "친구 거절",response = ResponseDTO.class)
    @RequestMapping(value = "/rejectfriend", method = RequestMethod.DELETE)
    public ResponseDTO rejectfriend(HttpServletRequest httpServletRequest,@RequestParam(value = "friendId")int id) {
        try {
            return friendsService.delete(httpServletRequest,id);
        }catch (Exception e){
            return new ResponseDTO("FAIL");
        }
    }


}
