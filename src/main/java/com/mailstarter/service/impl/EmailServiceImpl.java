package com.mailstarter.service.impl;

import com.mailstarter.config.EmailInformation;
import com.mailstarter.entity.EmailEnvelope;
import com.mailstarter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

/**
 * spring-mail-demo
 * phuc.tranngoc created on 8/17/2020
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_EMAIL = EmailInformation.USERNAME;

    @Override
    public void sendSimpleMessage(EmailEnvelope envelope) {
        // Create a simple mail message.
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(FROM_EMAIL);
        simpleMailMessage.setSubject("SIMPLE EMAIL - " + envelope.getSubject());
        simpleMailMessage.setText(envelope.getContent());

        // Send to receivers
        for(String _receiver: envelope.getReceivers()) {
            simpleMailMessage.setTo(_receiver);
            this.mailSender.send(simpleMailMessage);
        }
    }

    @Override
    public void sendMimeMessage(EmailEnvelope envelope) throws MessagingException {
        // Create a mime mail message.
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(FROM_EMAIL);
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
