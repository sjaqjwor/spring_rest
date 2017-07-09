package com.kusob.mapper;

import com.kusob.domain.wallet.Wallet;
import com.kusob.domain.wallet.WalletAddDTO;
import com.kusob.domain.wallet.WalletEditDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by kusob on 2017. 7. 8..
 */

@Mapper
public interface WalletMapper {
    List<Wallet> selectByEmail(String email);
    Wallet selectByWalletId(int walletId);
    void addWallet(WalletAddDTO walletAddDTO);
    void editWallet(WalletEditDTO walletEditDTO);
}