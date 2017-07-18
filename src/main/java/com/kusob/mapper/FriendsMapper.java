package com.kusob.mapper;

import com.kusob.domain.Friends.Friends;
import com.kusob.domain.Friends.FriendsAgreeDto;
import com.kusob.domain.member.MemberFreindDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by seungki on 2017-07-18.
 */
@Mapper
public interface FriendsMapper {
    void meetFriends(Friends friends);
    void agree(FriendsAgreeDto friendsAgreeDto);
    List<Integer> confirmMySide(int id);
    List<Integer> confirmCheck(int id);
    List<Integer> myFriend(int id);

}
