package com.mailstarter.service.impl;

import com.mailstarter.config.MailConfig;
import com.mailstarter.entity.MailDto;
import com.mailstarter.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/17/2020
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendSimpleMessage(MailDto email) {
        // Create a simple mail message.
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.getReceiver());
        simpleMailMessage.setFrom(MailConfig.USERNAME);
        simpleMailMessage.setSubject("SIMPLE EMAIL - " + email.getSubject());
        simpleMailMessage.setText(email.getStaticContent());

        // Send to receivers
        this.mailSender.send(simpleMailMessage);
    }

    @Override
    @Async
    public void sendMimeMessage(MailDto email, String receiver) throws MessagingException, UnsupportedEncodingException {
        System.err.println("Send to " + receiver);
        // Create a mime mail message.
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(receiver);
        helper.setFrom(MailConfig.USERNAME, MailConfig.SIGNATURE);
        helper.setSubject("ATTACHED EMAIL - " + email.getSubject());
        helper.setText(email.getStaticContent(), true);
        addAttachments(email, helper);
        // Send to receivers
        this.mailSender.send(mimeMessage);
    }

    @Override
    @Async
    public void sendTemplateMessage(MailDto email, String receiver) throws MessagingException, UnsupportedEncodingException {
        // Set Template
        Context context = new Context();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Phuc");
        map.put("code", "FHEUT4HFNSJA");
        email.setDynamicContents(map);
        context.setVariables(email.getDynamicContents());
        String htmlTemplate = templateEngine.process("email/activation", context);

        sendPreparedMail(email, receiver, htmlTemplate);

    }

    private void sendPreparedMail(MailDto email,String receiver, String htmlContent) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setFrom(MailConfig.USERNAME, MailConfig.SIGNATURE);
        mimeMessageHelper.setSubject("TEMPLATE EMAIL - " + email.getSubject());

        mimeMessageHelper.setText(htmlContent, true);
        addAttachments(email, mimeMessageHelper);
        mailSender.send(message);
    }

    private void addAttachments(MailDto email, MimeMessageHelper helper) throws MessagingException {
        // Add attachments if it's available
        if (Objects.nonNull(email.getAttachmentPaths())) {
            for (String path : email.getAttachmentPaths()
            ) {
                FileSystemResource file = new FileSystemResource(path);
                helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }
        }
    }
}
