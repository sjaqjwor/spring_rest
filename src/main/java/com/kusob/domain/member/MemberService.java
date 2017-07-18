package com.kusob.domain.member;

import com.kusob.config.JwtConfig.JwtResponseDto;
import com.kusob.config.JwtConfig.JwtService;
import com.kusob.domain.Friends.FriendSearchDto;
import com.kusob.domain.ResponseDTO;
import com.kusob.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kusob on 2017. 7. 5..
 */
@Slf4j
@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    public Member checkEmail(String email) {
        Member member = memberMapper.selectByEmail(email);
        if (member == null) { //중복이 아니라면 null을 리턴하는데, no content를 리턴하지않고 빈 json을 리턴하기위해서
            member = new Member(); //빈객체를 생성해서 보낸다
        }
        return member;
    }

    public AuthCode authCode(String email) {
        String authCode = String.valueOf((int) (Math.random() * 1000000));
        while (authCode.length() != 6) {
            Date date = new Date();
            char[] c = String.valueOf(date.getTime()).toCharArray();
            authCode += c[c.length - 1];
        }
        //받은 이메일로 인증코드를 발송한다.
        Properties properties = new Properties();
        properties.put("mail.smtp.user", "lsklsk4341@gmail.com"); //구글 계정
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        try {
            Authenticator auth = new MyAuthentication();
            Session session = Session.getInstance(properties, auth);
            session.setDebug(true); // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
            MimeMessage msg = new MimeMessage(session);

            msg.setSubject("회원가입을 위한 인증코드입니다!");
            Address fromAddr = new InternetAddress("lsklsk4341@gmail.com"); // 보내는사람 EMAIL
            msg.setFrom(fromAddr);
            Address toAddr = new InternetAddress(email);    //받는사람 EMAIL
            msg.addRecipient(Message.RecipientType.TO, toAddr);
            msg.setContent("<h1>인증코드는 : <span style='color:blue'>" + authCode + "</span> 입니다.</h1>", "text/html;charset=KSC5601"); //메일 전송될 내용
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        랜덤으로 생성한 인증코드를 리턴해준다
        AuthCode auth = new AuthCode();
        auth.setCode(authCode);
        return auth;
    }
    
    public ResponseDTO join(MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            memberMapper.join(memberDTO);
            responseDTO.setMessage("SUCCESS");
        } catch (Exception e) {
            responseDTO.setMessage("FAIL");
            System.out.println(e);
        }
        return responseDTO;
    }
    public JwtResponseDto login(MemberLoginDto memberLoginDto) throws AuthenticationException {
        //파라미터로 받은 이름 비번을 가지고 인증전에 객체 생성
        MemberDTO memberDTO = new MemberDTO(memberLoginDto.getEmail(),memberLoginDto.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberDTO.getEmail(),memberDTO.getPassword());
        String jwtToken =null;
        JwtResponseDto jwtResponseDto = null;
        try{
            //생성한 객체를 보내면 Manager가 userDetails를 거쳐서 인증함
            final Authentication authentication = authenticationManager.authenticate(authenticationToken);
            //인증하 객체를 security에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwtToken=jwtService.createToken(authenticationToken,authentication.getName());
            jwtResponseDto = new JwtResponseDto(jwtToken,"SUCCESS");
        }catch (Exception e){
            jwtResponseDto = new JwtResponseDto("인증실패","FAIL");
            System.out.println(e);
        }
        return jwtResponseDto;
    }

    public Map searchFriend(String name, HttpServletRequest httpServletRequest){
        List<MemberFreindDto> searchFriends=null;
        Map<Integer,MemberFreindDto> fmap = new HashMap<>();
        try {
            String token = httpServletRequest.getHeader("Authorization");
            int id = jwtService.idFromToken(token);
            searchFriends = new ArrayList<>();
            FriendSearchDto fd = new FriendSearchDto(name,id);
            searchFriends=memberMapper.selectByFriend(fd);
            for(int a=0;a<searchFriends.size();a++){
                fmap.put(a+1,searchFriends.get(a));
            }
            return fmap;
        }catch (Exception e){
            fmap=new HashMap<>();
            fmap.put(0,null);
        }
        return fmap;
    }
}

