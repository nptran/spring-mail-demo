package com.mailstarter.service;

import com.mailstarter.entity.EmailEnvelope;

import javax.mail.MessagingException;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/18/2020
 */
public interface EmailService {

    public void sendSimpleMessage(EmailEnvelope envelope);

    public void sendMimeMessage(EmailEnvelope envelope) throws MessagingException;

}
