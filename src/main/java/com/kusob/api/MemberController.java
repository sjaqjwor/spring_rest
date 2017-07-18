package com.kusob.api;

import com.kusob.config.JwtConfig.JwtResponseDto;
import com.kusob.config.JwtConfig.JwtService;
import com.kusob.domain.ResponseDTO;
import com.kusob.domain.member.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by kusob on 2017. 7. 5..
 */
@Slf4j
@Api(value = "멤버 API", description = "coin.member Table API", basePath = "/api/member")
@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    JwtService jwtService;

    @ApiOperation(value = "이메일로 멤버 검색", notes = "이메일로 멤버 검색 혹은 이메일 중복검사")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Member allList(@ApiParam(value = "이메일 주소")
                                 @RequestParam(value = "email") String email) {
        try {
            return memberService.checkEmail(email);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @ApiOperation(value = "이메일 인증", notes = "입력한 이메일로 인증코드 전송")
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public AuthCode authCode(@ApiParam(value = "인증 메일을 받을 이메일 주소")
                          @RequestParam(value = "email") String email) {
        try {
            return memberService.authCode(email);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @ApiOperation(value = "회원가입", notes = "회원가입 api")
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ResponseDTO join(@ApiParam(value = "member obj") @RequestBody MemberDTO memberDTO) {
        try {
            return memberService.join(memberDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }
    @ApiOperation(value = "로그인", notes="로그인 api")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public JwtResponseDto login(@RequestBody MemberLoginDto memberLoginDto)  throws AuthenticationException {
        try{
            return memberService.login(memberLoginDto);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return null;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value ="authorization header", required = true,
                    dataType = "string", paramType = "Header")
    })
    @ApiOperation(value = "로그인확인", notes="로그인확인 api")
    @RequestMapping(value="/loginConfirm",method = RequestMethod.GET)
    public JwtResponseDto loginConfirm(HttpServletRequest httpServletRequest)  throws AuthenticationException {
        try{
            String token = httpServletRequest.getHeader("Authorization");
            Date date = jwtService.expFromToken(token);
            System.out.println(date);
            System.out.print("성공");
            return null;
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return null;
    }
}
