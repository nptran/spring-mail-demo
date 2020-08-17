package com.example.mailstarter.controller;

import com.example.mailstarter.entity.AttachedEmail;
import com.example.mailstarter.entity.BaseEmail;
import com.example.mailstarter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.File;

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
    public String sendSimpleEmail(@RequestBody BaseEmail baseEmail) {
        emailService.sendSimpleEmail(baseEmail);
        return "Mail sent!";
    }

    @ResponseBody
    @PostMapping("/sendAttachedMail")
    public String sendAttachedMail(@RequestBody AttachedEmail attachedEmail) throws MessagingException {
        emailService.sendAttachedEmail(attachedEmail);
        return "Mail Sent";
    }

    @ResponseBody
    @PostMapping("/sendHTMLMail")
    public String sendHTMLMail(@RequestBody BaseEmail baseEmail) throws MessagingException {
        emailService.sendHTMLEmail(baseEmail);
        return "Mail Sent";
    }

}
