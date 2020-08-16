package com.example.mailstarter.controller;

import com.example.mailstarter.config.MailInformation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * mailstarter
 * phuc.tranngoc created on 8/13/2020
 */
@Controller
@RequestMapping("/autoEmail")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @ResponseBody
    @GetMapping("/sendSimpleMail")
    public String sendSimpleEmail(@RequestParam String receiver) {

        // Create a simple mail message.
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(MailInformation.USERNAME);
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSubject("Test for sending a simple mail message");
        simpleMailMessage.setText("Xin chào! Tôi là một simple mail.");

        // Send the message
        this.mailSender.send(simpleMailMessage);

        return "Mail sent!";
    }

    @ResponseBody
    @GetMapping("/sendAttachmentMail")
    public String sendAttachmentMail(@RequestParam String receiver) throws MessagingException {
        FileSystemResource file1 = new FileSystemResource(new File("E:\\a1.jpg"));
        FileSystemResource file2 = new FileSystemResource(new File("E:\\a2.jpg"));

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(MailInformation.USERNAME);
        helper.setTo(receiver);
        helper.setSubject("Test for sending an attachment mail message");
        helper.setText("This is an attachment email.", false);
        helper.addAttachment("Đính kèm 1", file1);
        helper.addAttachment("Đính kèm 2", file2);

        this.mailSender.send(mimeMessage);

        return "Mail Sent";
    }

    @ResponseBody
    @GetMapping("/sendHTMLMail")
    public String sendHTMLMail(@RequestParam String receiver) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String htmlMsg = "<h3>Im testing send a HTML email</h3>"
                + "<img src='http://www.apache.org/images/asf_logo_wide.gif'>";

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(MailInformation.USERNAME);
        helper.setTo(receiver);
        helper.setSubject("Test for sending a HTML mail message");
        helper.setText(htmlMsg, true);

        this.mailSender.send(mimeMessage);

        return "Mail Sent";
    }

}
