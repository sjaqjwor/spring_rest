package com.kusob.mapper;

import com.kusob.domain.MemberToWallets.MemberToWallets;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by seungki on 2017-07-18.
 */
@Mapper
public interface MemberToWalletsMapper {
    void MemeberToWallet(MemberToWallets memberToWallets);
    List<Integer> selectById(int id);
}
