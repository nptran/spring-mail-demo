package com.example.mailstarter.service.impl;

import com.example.mailstarter.config.MailInformation;
import com.example.mailstarter.entity.AttachedEmail;
import com.example.mailstarter.entity.BaseEmail;
import com.example.mailstarter.service.EmailService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    public void sendSimpleEmail(BaseEmail email) {
        // Create a simple mail message.
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(FROM_EMAIL);
        simpleMailMessage.setTo(email.getReceiver());
        simpleMailMessage.setSubject("SIMPLE EMAIL - " + email.getSubject());
        simpleMailMessage.setText(email.getContent());

        // Send the message
        this.mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendHTMLEmail(BaseEmail email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(FROM_EMAIL);
        helper.setTo(email.getReceiver());
        helper.setSubject("HTML EMAIL - " + email.getSubject());
        helper.setText(email.getContent(), true);

        this.mailSender.send(mimeMessage);
    }

    @Override
    public void sendAttachedEmail(AttachedEmail attachedEmail) throws MessagingException {
        List<FileSystemResource> attachments = new ArrayList<>();
        for (String path : attachedEmail.getAttachmentPaths()
        ) {
            FileSystemResource file = new FileSystemResource(path);
            attachments.add(file);
        }
        FileSystemResource file2 = new FileSystemResource(new File("E:\\a2.jpg"));
        attachments.add(file2);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(FROM_EMAIL);
        helper.setTo(attachedEmail.getReceiver());
        helper.setSubject("ATTACHED EMAIL - " + attachedEmail.getSubject());
        helper.setText("Nội dung", false);
        for (FileSystemResource file : attachments
        ) {
            helper.addAttachment(Objects.requireNonNull(FilenameUtils.getName(file.getFilename())), file);
        }

        this.mailSender.send(mimeMessage);
    }
}
