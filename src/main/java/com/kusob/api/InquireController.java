package com.kusob.api;

import com.kusob.domain.ResponseDTO;
import com.kusob.domain.inquire.Inquire;
import com.kusob.domain.inquire.InquireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kusob on 2017. 7. 10..
 */
@Slf4j
@Api(value = "문의하기 API", description = "더보기-문의하기 API", basePath = "/api/inquire")
@RestController
@RequestMapping("/api/inquire")
public class InquireController {
    @Autowired
    InquireService inquireService;

    @ApiOperation(value = "문의하기 보내기", notes = "문의하기 api")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseDTO sendInquireMail(@ApiParam(value = "내용, 답변 받을 이메일 주소") @RequestBody Inquire inquire) {
        try {
            return inquireService.sendInquireMail(inquire);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

}
