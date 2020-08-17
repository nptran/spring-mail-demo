package com.example.mailstarter.service.impl;

import com.example.mailstarter.config.MailInformation;
import com.example.mailstarter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/17/2020
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_EMAIL = MailInformation.USERNAME;

    @Override
    public void sendSimpleEmail(String receiver, String subject, String content) {
        // Create a simple mail message.
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(FROM_EMAIL);
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSubject("SIMPLE EMAIL - " + subject);
        simpleMailMessage.setText(content);

        // Send the message
        this.mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendHTMLEmail(String receiver, String subject, String htmlContent) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(FROM_EMAIL);
        helper.setTo(receiver);
        helper.setSubject("HTML EMAIL - " + subject);
        helper.setText(htmlContent, true);

        this.mailSender.send(mimeMessage);
    }

    @Override
    public void sendAttachedEmail(String receiver, String subject, String content) throws MessagingException {
        FileSystemResource file1 = new FileSystemResource(new File("E:\\a1.jpg"));
        FileSystemResource file2 = new FileSystemResource(new File("E:\\a2.jpg"));

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(FROM_EMAIL);
        helper.setTo(receiver);
        helper.setSubject("ATTACHED EMAIL - " + subject);
        helper.setText(content, false);
        helper.addAttachment(Objects.requireNonNull(file1.getFilename()), file1);
        helper.addAttachment(Objects.requireNonNull(file2.getFilename()), file2);

        this.mailSender.send(mimeMessage);
    }
}
