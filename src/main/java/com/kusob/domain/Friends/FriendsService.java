package com.kusob.domain.Friends;

import com.kusob.config.JwtConfig.JwtService;
import com.kusob.domain.ResponseDTO;
import com.kusob.domain.member.MemberFreindDto;
import com.kusob.mapper.FriendsMapper;
import com.kusob.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map confirmMySide(HttpServletRequest httpServletRequest){
        List<Integer> list = null;
        Map<Integer,MemberFreindDto> map = new HashMap<>();
        try{
            list = new ArrayList<>();
            String token = httpServletRequest.getHeader("Authorization");
            int myid = jwtService.idFromToken(token);
            list=friendsMapper.confirmMySide(myid);
            for(int a=0;a<list.size();a++){
                MemberFreindDto m1=memberMapper.selectById(list.get(a));
                map.put(a+1,m1);
            }
            return map;
        }catch (Exception e){
            map= new HashMap<>();
            map.put(0,null);
            return map;
        }
    }
    public Map confirmCheck(HttpServletRequest httpServletRequest){
        List<Integer> list = null;
        Map<Integer,MemberFreindDto> map = new HashMap<>();
        try{
            list = new ArrayList<>();
            String token = httpServletRequest.getHeader("Authorization");
            int myid = jwtService.idFromToken(token);
            list=friendsMapper.confirmCheck(myid);
            for(int a=0;a<list.size();a++){
                MemberFreindDto m1=memberMapper.selectById(list.get(a));
                map.put(a+1,m1);
            }
            return map;
        }catch (Exception e){
            map= new HashMap<>();
            map.put(0,null);
            return map;
        }
    }
    /*public List<MemberFreindDto> myFriend(HttpServletRequest httpServletRequest){
        try {
                String token = httpServletRequest.getHeader("Authorization");
                int id = jwtService.idFromToken(token);
        }
    }*/
}
