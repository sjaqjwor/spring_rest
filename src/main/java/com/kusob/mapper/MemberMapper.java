package com.kusob.mapper;

import com.kusob.domain.Friends.FriendSearchDto;
import com.kusob.domain.member.Member;
import com.kusob.domain.member.MemberDTO;
import com.kusob.domain.member.MemberFreindDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kusob on 2017. 7. 4..
 */

@Mapper
public interface MemberMapper {
    Member selectByEmail(String email);
    Member selectByLoginEmail(String email);
    void join(MemberDTO memberDTO);
    List<MemberFreindDto> selectByFriend(FriendSearchDto friendSearchDto);
    MemberFreindDto selectById(int id);
}