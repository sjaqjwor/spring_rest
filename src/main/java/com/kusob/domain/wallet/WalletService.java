package com.kusob.domain.wallet;

import com.kusob.config.JwtConfig.JwtService;
import com.kusob.domain.MemberToWallets.MemberToWallets;
import com.kusob.domain.ResponseDTO;
import com.kusob.mapper.MemberMapper;
import com.kusob.mapper.MemberToWalletsMapper;
import com.kusob.mapper.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by kusob on 2017. 7. 8..
 */

@Service
public class WalletService {


    @Autowired
    JwtService jwtService;

    @Autowired
    MemberToWalletsMapper memberToWalletsMapper;

    @Autowired
    WalletMapper walletMapper;

    public Map getWalletList(HttpServletRequest httpServletRequest) throws Exception{
        List<Integer> list =null;
        List<Wallet> w_list=null;
        Map<Integer,Wallet> mylist = new HashMap<>();
        try{
            String token = httpServletRequest.getHeader("Authorization");
            int id = jwtService.idFromToken(token);
            list = new ArrayList<>();
            list = memberToWalletsMapper.selectById(id);
            for(int num : list){
                w_list.add(walletMapper.selectByWalletId(num));
            }
            for(int a=0;a<w_list.size();a++){
                mylist.put(a+1,w_list.get(a));
            }
        }catch (Exception e){
            throw new Exception();
        }
        return mylist;

    }
/*
    public Wallet getWallet(int walletId){
        //Wallet wallet = walletMapper.selectByWalletId(walletId);
        if(wallet==null){
            wallet = new Wallet();
        }
        return wallet;
    }
*/
    public ResponseDTO addWallet(String name,String wallet, HttpServletRequest httpServletRequest) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            WalletAddDTO w = new WalletAddDTO();
            w.setWalletName(name);
            w.setWalletAddr(wallet);
            w.setWalletQr("아직");
            walletMapper.addWallet(w);
            String token = httpServletRequest.getHeader("Authorization");
            int id = jwtService.idFromToken(token);
            MemberToWallets mtw = new MemberToWallets(id,w.getWallet_id());
            memberToWalletsMapper.MemeberToWallet(mtw);
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
            //walletMapper.editWallet(walletEditDTO);
            responseDTO.setMessage("SUCCESS");
        } catch (Exception e) {
            responseDTO.setMessage("FAIL");
            System.out.println(e);
        }
        return responseDTO;
    }
}

