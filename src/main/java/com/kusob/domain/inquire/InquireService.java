package com.kusob.domain.inquire;

import com.kusob.domain.ResponseDTO;
import com.kusob.domain.member.MyAuthentication;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by kusob on 2017. 7. 10..
 */
@Service
public class InquireService {
    public ResponseDTO sendInquireMail(Inquire inquire) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            //todo
            Properties properties = new Properties();
            properties.put("mail.smtp.user", inquire.getSenderEmail()); //구글 계정
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

                msg.setSubject(inquire.getSenderEmail()+"님이 보내신 문의사항");
                Address fromAddr = new InternetAddress(inquire.getSenderEmail()); // 보내는사람 EMAIL
                msg.setFrom(fromAddr);
                Address toAddr = new InternetAddress("holein0ne@naver.com");    //받는사람 EMAIL
                msg.addRecipient(Message.RecipientType.TO, toAddr);
                msg.setContent(
                        "<h3>답변 보낼 메일주소 : <span style='color:blue'>" 
                                + inquire.getSenderEmail() + "</span></h3><br/>"
                                +inquire.getContent().replaceAll("\n","<br/>"), "text/html;charset=KSC5601"); //메일 전송될 내용
                Transport.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            responseDTO.setMessage("SUCCESS");
        } catch (Exception e) {
            responseDTO.setMessage("FAIL");
            e.printStackTrace();
        }
        return responseDTO;
    }
}


