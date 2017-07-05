package com.kusob.domain.member;

import javax.mail.PasswordAuthentication;

/**
 * Created by kusob on 2017. 7. 5..
 */

public class MyAuthentication extends javax.mail.Authenticator {

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("lsklsk4341@gmail.com", "dltmdrl1"); // @gmail.com 제외한 계정 ID, PASS

    }
}