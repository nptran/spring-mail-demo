package com.example.mailstarter.controller;

import com.example.mailstarter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

/**
 * mailstarter
 * phuc.tranngoc created on 8/13/2020
 */
@Controller
@RequestMapping("/autoEmail")
public class MailController {

    @Autowired
    private EmailService emailService;

    @ResponseBody
    @PostMapping("/sendSimpleMail")
    public String sendSimpleEmail(@RequestParam String receiver, @RequestParam String subject, @RequestParam String content) {
        emailService.sendSimpleEmail(receiver, subject, content);
        return "Mail sent!";
    }

    @ResponseBody
    @PostMapping("/sendAttachedMail")
    public String sendAttachedMail(@RequestParam String receiver, @RequestParam String subject, @RequestParam String content) throws MessagingException {
        emailService.sendAttachedEmail(receiver, subject, content);
        return "Mail Sent";
    }

    @ResponseBody
    @PostMapping("/sendHTMLMail")
    public String sendHTMLMail(@RequestParam String receiver, @RequestParam String subject, @RequestParam String htmlContent) throws MessagingException {
        emailService.sendHTMLEmail(receiver, subject, htmlContent);
        return "Mail Sent";
    }

}
