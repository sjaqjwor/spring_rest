package com.kusob.mapper;

import com.kusob.domain.member.Member;
import com.kusob.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kusob on 2017. 7. 4..
 */

@Mapper
public interface MemberMapper {
    Member selectByEmail(String email);
    void join(MemberDTO memberDTO);
}