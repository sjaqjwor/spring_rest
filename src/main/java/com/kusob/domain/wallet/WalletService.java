package com.kusob.domain.wallet;

import com.kusob.domain.ResponseDTO;
import com.kusob.mapper.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kusob on 2017. 7. 8..
 */

@Service
public class WalletService {
    @Autowired
    WalletMapper walletMapper;

    public List<Wallet> getWalletList(String email) {
        List<Wallet> list = walletMapper.selectByEmail(email);
        if (list == null) { //중복이 아니라면 null을 리턴하는데, no content를 리턴하지않고 빈 json을 리턴하기위해서
            list = new LinkedList<>(); //빈객체를 생성해서 보낸다
        }
        return list;
    }

    public ResponseDTO addWallet(WalletAddDTO walletAddDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            walletMapper.addWallet(walletAddDTO);
            responseDTO.setMessage("SUCCESS");
        } catch (Exception e) {
            responseDTO.setMessage("FAIL");
            System.out.println(e);
        }
        return responseDTO;
    }

    public ResponseDTO editWallet(WalletEditDTO walletEditDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            walletMapper.editWallet(walletEditDTO);
            responseDTO.setMessage("SUCCESS");
        } catch (Exception e) {
            responseDTO.setMessage("FAIL");
            System.out.println(e);
        }
        return responseDTO;
    }
}

