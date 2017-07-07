package com.kusob.api;

import com.kusob.domain.ResponseDTO;
import com.kusob.domain.wallet.Wallet;
import com.kusob.domain.wallet.WalletAddDTO;
import com.kusob.domain.wallet.WalletEditDTO;
import com.kusob.domain.wallet.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "나의 지갑 리스트", notes = "이메일주소에 해당하는 멤버의 지갑 리스트")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Wallet> walletList(@ApiParam(value = "이메일 주소")
                          @RequestParam(value = "email") String email) {
        try {
            return walletService.getWalletList(email);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @ApiOperation(value = "지갑 추가", notes = "지갑추가 api")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseDTO add(@ApiParam(value = "지갑추가할때 필요한 정보들") @RequestBody WalletAddDTO walletAddDTO) {
        try {
            return walletService.addWallet(walletAddDTO);
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
