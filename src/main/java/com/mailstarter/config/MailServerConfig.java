package com.mailstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * mailstarter
 * phuc.tranngoc created on 8/13/2020
 *
 * It can be set on `application.properties` too.
 */
@Configuration
public class MailServerConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        //Set host and port for Gmail
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);

        //Set username and password for MailSender
        sender.setUsername(SenderInformation.USERNAME);
        sender.setPassword(SenderInformation.PASSWORD);

        Properties properties = sender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

        return sender;
    }
}
