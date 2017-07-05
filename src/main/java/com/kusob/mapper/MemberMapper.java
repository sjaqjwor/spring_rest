package com.kusob.mapper;

import com.kusob.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by kusob on 2017. 7. 4..
 */

@Mapper
public interface MemberMapper {
    Member selectByEmail(String email);
//    List<User> selectSearch(@Param("selgisu")int selgisu,@Param("searchsel") int searchsel,@Param("searchtxt") String searchtxt);
//    List<User> selectByUid(String uid);
//    void updateMng(@Param("uid")String uid,@Param("ustatus")String ustatus, @Param("u_id")String u_id, @Param("u_status")String u_status);
}