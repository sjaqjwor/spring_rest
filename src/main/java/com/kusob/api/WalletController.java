package com.kusob.api;

import com.kusob.config.JwtConfig.JwtService;
import com.kusob.domain.wallet.ListWalletResponse;
import com.kusob.domain.ResponseDTO;
import com.kusob.domain.wallet.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    JwtService jwtService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "나의 지갑 리스트", notes = "토큰값 넘겨주세요~",response = ListWalletResponse.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ListWalletResponse walletList(HttpServletRequest httpServletRequest) {
        try {
            String token = httpServletRequest.getHeader("Authorization");
            int id=jwtService.idFromToken(token);
            return walletService.getWalletList(id);
        } catch (Exception e) {
            return new ListWalletResponse("FAIL",null);
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "지갑 추가", notes = "지갑추가 api",response = ResponseDTO.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseDTO add(@RequestBody WalletRequestDto walletRequestDto , HttpServletRequest httpServletRequest) {
        try {
            return walletService.addWallet(walletRequestDto,httpServletRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "지갑 관리, 변경", notes = "지갑변경 api",response = ResponseDTO.class)
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseDTO edit(@ApiParam(value = "지갑변경할때 필요한 정보들") @RequestBody WalletEditDTO walletEditDTO) {
        try {
            return walletService.editWallet(walletEditDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "지갑 삭제", notes = "지갑 삭제 api",response = ResponseDTO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseDTO delete(HttpServletRequest httpServletRequest,@RequestParam(value = "WalletId") int id){
        try {
            return walletService.delete(id,httpServletRequest);
        }catch (Exception e){
            return new ResponseDTO("FAIL");
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "지갑 한개 정보가져오기", notes = "지갑 정보 api",response = WalletResponse.class)
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public WalletResponse info(HttpServletRequest httpServletRequest,@RequestParam(value = "WalletId") int id){
        try {
            return walletService.info(id);
        }catch (Exception e){
            return new WalletResponse("FAIL",null);
        }
    }

}
