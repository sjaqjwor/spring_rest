package com.kusob.domain.Friends;

import com.kusob.config.JwtConfig.JwtService;
import com.kusob.domain.member.ListMemberFriendResponseDto;
import com.kusob.domain.ResponseDTO;
import com.kusob.domain.member.MemberFreindDto;
import com.kusob.mapper.FriendsMapper;
import com.kusob.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seungki on 2017-07-18.
 */
@Service
public class FriendsService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private MemberMapper memberMapper;

    public ResponseDTO meetFriend(int id, HttpServletRequest httpServletRequest){
        try {
            String token = httpServletRequest.getHeader("Authorization");
            int myid = jwtService.idFromToken(token);
            if(friendsMapper.existFrined(new FriendsAgreeDto(myid,id))==null){
                return new ResponseDTO("EXIST");
            }
            Friends my = new Friends(myid,id,0);
            Friends other = new Friends(id,myid,1);
            friendsMapper.meetFriends(my);
            friendsMapper.meetFriends(other);
            return new ResponseDTO("SUCCESS");
        }catch (Exception e){
            return new ResponseDTO("FAIL");
        }
    }

    public ResponseDTO agreeFriend(int id,HttpServletRequest httpServletRequest){
        try{
            String token = httpServletRequest.getHeader("Authorization");
            int myid = jwtService.idFromToken(token);
            int other = id;
            FriendsAgreeDto fagree = new FriendsAgreeDto(myid,id);
            friendsMapper.agree(fagree);
            return new ResponseDTO("SUCCESS");
        }catch (Exception e){
            return new ResponseDTO("FAIL");
        }
    }
    public ListMemberFriendResponseDto confirmMySide(HttpServletRequest httpServletRequest) throws Exception{
        List<Integer> list = null;
        List<MemberFreindDto> mf_list = new ArrayList<>();
        try{
            list = new ArrayList<>();
            String token = httpServletRequest.getHeader("Authorization");
            int myid = jwtService.idFromToken(token);
            list=friendsMapper.confirmMySide(myid);
            for(int a=0;a<list.size();a++){
                MemberFreindDto m1=memberMapper.selectById(list.get(a));
                mf_list.add(m1);
            }
            return new ListMemberFriendResponseDto("SUCCESS",mf_list);
        }catch (Exception e){
            throw new Exception();
        }
    }
    public ListMemberFriendResponseDto confirmCheck(HttpServletRequest httpServletRequest)throws Exception{
        List<Integer> list = null;
        List<MemberFreindDto> mf_list = new ArrayList<>();
        try{
            list = new ArrayList<>();
            String token = httpServletRequest.getHeader("Authorization");
            int myid = jwtService.idFromToken(token);
            list=friendsMapper.confirmCheck(myid);
            for(int a=0;a<list.size();a++){
                MemberFreindDto m1=memberMapper.selectById(list.get(a));
                mf_list.add(m1);
            }
            return new ListMemberFriendResponseDto("SUCCESS",mf_list);
        }catch (Exception e){
            throw  new Exception();
        }
    }
    public ListMemberFriendResponseDto myfriend(HttpServletRequest httpServletRequest) throws Exception{
        String token = httpServletRequest.getHeader("Authorization");
        int id = jwtService.idFromToken(token);
        List<MemberFreindDto> f_list = null;
        try{
            List<Integer> list =friendsMapper.myFriend(id);
            f_list = new ArrayList<>();
            for(int a=0;a<list.size();a++){
                f_list.add(memberMapper.selectById(list.get(a)));
            }
            return new ListMemberFriendResponseDto("SUCCESS",f_list);
        }catch (Exception e){
           throw new Exception();
        }

    }
    public ResponseDTO delete(HttpServletRequest httpServletRequest,int id) throws Exception{
        try{
            String token=httpServletRequest.getHeader("Authorization");
            int myid= jwtService.idFromToken(token);
            friendsMapper.deleteFrined(new FriendDeleteDto(myid,id));
            return new ResponseDTO("SUCCESS");
        }catch (Exception e){
            throw new Exception();
        }
    }


}
