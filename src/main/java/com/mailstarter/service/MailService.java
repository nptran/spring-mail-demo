package com.mailstarter.service;

import com.mailstarter.entity.MailEnvelope;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/18/2020
 */
public interface MailService {

    public void sendSimpleMessage(MailEnvelope envelope);

    public void sendMimeMessage(MailEnvelope envelope, String receiver) throws MessagingException, UnsupportedEncodingException;

}
