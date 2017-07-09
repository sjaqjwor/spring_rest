package com.kusob.api;

import com.kusob.domain.priceInfo.PriceInfo;
import com.kusob.domain.priceInfo.PriceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kusob on 2017. 7. 8..
 */
@Slf4j
@Api(value = "시세정보 API", description = "시세정보 API", basePath = "/api/price")
@RestController
@RequestMapping("/api/price")
public class PriceInfoController {
    @Autowired
    PriceInfoService priceInfoService;

    @ApiOperation(value = "시세정보", notes = "6개 주요 사이트에 대한 암호화폐 시세정보")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public List<PriceInfo> info() {
        try {
            return priceInfoService.getPrice();
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
