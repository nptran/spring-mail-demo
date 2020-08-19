package com.mailstarter.service;

import com.mailstarter.entity.MailDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/18/2020
 */
public interface MailService {

    public void sendSimpleMessage(MailDto email);

    public void sendMimeMessage(MailDto email, String receiver) throws MessagingException, UnsupportedEncodingException;

    public void sendTemplateMessage(MailDto email, String receiver) throws MessagingException, UnsupportedEncodingException;

}
