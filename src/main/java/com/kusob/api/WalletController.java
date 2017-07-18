package com.kusob.api;

import com.kusob.domain.ResponseDTO;
import com.kusob.domain.wallet.Wallet;
import com.kusob.domain.wallet.WalletAddDTO;
import com.kusob.domain.wallet.WalletEditDTO;
import com.kusob.domain.wallet.WalletService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kusob on 2017. 7. 8..
 */
@Slf4j
@Api(value = "지갑 API", description = "coin.wallet Table API", basePath = "/api/wallet")
@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "나의 지갑 리스트", notes = "이메일주소에 해당하는 멤버의 지갑 리스트")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Map walletList(HttpServletRequest httpServletRequest) {
        try {
            return walletService.getWalletList(httpServletRequest);
        } catch (Exception e) {
            Map<Integer,String > h = new HashMap<>();
            h.put(0,"FAIL");
            return h;
        }
    }

    @ApiOperation(value = "wallet id로 조회한 지갑 1개의 상세 정보", notes = "walletId로 검색하여 지갑정보를 가져온다")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Wallet info(@ApiParam(value = "DB의 wallet id")
                                   @RequestParam(value = "walletId") int walletId) {
        try {
            return null;
            //return walletService.getWallet(walletId);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "지갑 추가", notes = "지갑추가 api")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseDTO add(@RequestParam(value = "walletName") String name,
                           @RequestParam(value = "walletAddr") String addr, HttpServletRequest httpServletRequest) {
        try {
            return walletService.addWallet(name,addr,httpServletRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @ApiOperation(value = "지갑 관리, 변경", notes = "지갑변경 api")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseDTO edit(@ApiParam(value = "지갑변경할때 필요한 정보들") @RequestBody WalletEditDTO walletEditDTO) {
        try {
            return walletService.editWallet(walletEditDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }
}
