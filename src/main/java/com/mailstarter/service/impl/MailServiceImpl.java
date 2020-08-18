package com.mailstarter.service.impl;

import com.mailstarter.config.SenderInformation;
import com.mailstarter.entity.MailEnvelope;
import com.mailstarter.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/17/2020
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMessage(MailEnvelope envelope) {
        // Create a simple mail message.
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(SenderInformation.USERNAME);
        simpleMailMessage.setSubject("SIMPLE EMAIL - " + envelope.getSubject());
        simpleMailMessage.setText(envelope.getContent());

        // Send to receivers
        for(String _receiver: envelope.getReceivers()) {
            simpleMailMessage.setTo(_receiver);
            this.mailSender.send(simpleMailMessage);
        }
    }

    @Override
    public void sendMimeMessage(MailEnvelope envelope) throws MessagingException, UnsupportedEncodingException {
        // Create a mime mail message.
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(SenderInformation.USERNAME, SenderInformation.SIGNATURE);
        helper.setSubject("ATTACHED EMAIL - " + envelope.getSubject());
        helper.setText(envelope.getContent(), true);

        // Add attachments if it's available
        if (Objects.nonNull(envelope.getAttachmentPaths())) {
            for (String path : envelope.getAttachmentPaths()
            ) {
                FileSystemResource file = new FileSystemResource(path);
                helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }
        }

        // Send to receivers
        for(String _receiver: envelope.getReceivers()) {
            helper.setTo(_receiver);
            this.mailSender.send(mimeMessage);
        }
    }
}