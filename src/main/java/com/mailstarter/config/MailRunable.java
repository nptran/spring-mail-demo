package com.mailstarter.config;

import com.mailstarter.entity.MailDto;
import com.mailstarter.service.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/21/2020
 */
@NoArgsConstructor
public class MailRunable implements Runnable {

    private String receiver;

    private MailDto mailDto;

    @Autowired
    private MailService mailService;

    public MailRunable (String receiver, MailDto mailDto) {
        this.receiver = receiver;
        this.mailDto = mailDto;
    }

    @Override
    public void run() {
        try {
            mailService.sendTemplateMessage(mailDto, receiver);
        } catch (UnsupportedEncodingException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
