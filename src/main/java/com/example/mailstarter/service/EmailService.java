package com.example.mailstarter.service;

import javax.mail.MessagingException;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/17/2020
 */
public interface EmailService {

    public void sendSimpleEmail(String receiver, String subject, String content);

    public void sendHTMLEmail(String receiver, String subject, String htmlContent) throws MessagingException;

    public void sendAttachedEmail(String receiver, String subject, String content) throws MessagingException;

}
