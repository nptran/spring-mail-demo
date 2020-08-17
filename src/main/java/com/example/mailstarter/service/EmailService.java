package com.example.mailstarter.service;

import com.example.mailstarter.entity.AttachedEmail;
import com.example.mailstarter.entity.BaseEmail;

import javax.mail.MessagingException;
import java.io.File;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/17/2020
 */
public interface EmailService {

    public void sendSimpleEmail(BaseEmail email);

    public void sendHTMLEmail(BaseEmail email) throws MessagingException;

    public void sendAttachedEmail(AttachedEmail attachedEmail) throws MessagingException;

}
