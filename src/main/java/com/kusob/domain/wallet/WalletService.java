package com.kusob.domain.wallet;

import com.kusob.config.JwtConfig.JwtService;
import com.kusob.domain.MemberToWallets.MemberToWallets;
import com.kusob.domain.ResponseDTO;
import com.kusob.mapper.MemberToWalletsMapper;
import com.kusob.mapper.WalletMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by kusob on 2017. 7. 8..
 */

@Service
@Slf4j
public class WalletService {


    @Autowired
    JwtService jwtService;

    @Autowired
    MemberToWalletsMapper memberToWalletsMapper;

    @Autowired
    WalletMapper walletMapper;

    public ListWalletResponse getWalletList(int id) throws Exception{
        List<Integer> list ;
        List<Wallet> w_list=null;
        try{
            list = memberToWalletsMapper.selectById(id);
            w_list=new ArrayList<>();
            for(int a=0;a<list.size();a++){
                w_list.add(walletMapper.walletList(list.get(a)));
            }

           return new ListWalletResponse("SUCCESS",w_list);
        }catch (Exception e){
            throw new Exception();
        }

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
    public ResponseDTO addWallet(WalletRequestDto walletRequestDto, HttpServletRequest httpServletRequest) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            WalletAddDTO w = new WalletAddDTO();
            w.setWalletName(walletRequestDto.getWalletName());
            w.setWalletAddr(walletRequestDto.getWalletAddr());
            w.setWalletType(walletRequestDto.getWalletType());
            walletMapper.addWallet(w);
            String token = httpServletRequest.getHeader("Authorization");
            int id = jwtService.idFromToken(token);
            MemberToWallets mtw = new MemberToWallets(id,w.getWallet_id());
            memberToWalletsMapper.MemeberToWallet(mtw);
            responseDTO.setMessage("SUCCESS");
        } catch (Exception e) {
            responseDTO.setMessage("FAIL");


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

        }
        return responseDTO;
    }

    public ResponseDTO delete(int id,HttpServletRequest httpServletRequest) throws Exception{
        try{
            walletMapper.deleteWallet(id);
            return new ResponseDTO("SUCCESS");
        }catch (Exception e){
            throw  new Exception();
        }

    }

    public WalletResponse info(int id) throws Exception{
        try {
            return new WalletResponse("SUCCESS",walletMapper.walletList(id));
        }catch (Exception e){
            throw new Exception();
        }
    }
}

