package com.mailstarter.controller;

import com.mailstarter.entity.MailDto;
import com.mailstarter.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * mailstarter
 * phuc.tranngoc created on 8/13/2020
 */
@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendSimpleMessage")
    public ResponseEntity<Void> sendSimpleMessage(@RequestBody MailDto envelope) {
        mailService.sendSimpleMessage(envelope);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sendMimeMessage")
    public ResponseEntity<?> sendMimeMessage(@RequestBody MailDto envelope) throws MessagingException, UnsupportedEncodingException {

        long startTime = System.nanoTime();
        mailService.sendMimeMessage(envelope, "boybibo98@gmail.com");
        mailService.sendMimeMessage(envelope, "lamngungo@gmail.com");
        mailService.sendMimeMessage(envelope, "lowelowe1998@gmail.com");
        mailService.sendMimeMessage(envelope, "nptran9810@gmail.com");
        mailService.sendMimeMessage(envelope, "minhdat98hy@gmail.com");

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        return new ResponseEntity<>(duration/1000000, HttpStatus.OK);
    }

    @PostMapping("/sendTemplateMessage")
    public ResponseEntity<?> sendTemplateMessage(@RequestBody MailDto email) throws MessagingException, UnsupportedEncodingException {
        long startTime = System.nanoTime();
        mailService.sendTemplateMessage(email, "nptran9810@gmail.com");
        mailService.sendTemplateMessage(email, "boybibo98@gmail.com");
        mailService.sendTemplateMessage(email, "lamngungo@gmail.com");
        mailService.sendTemplateMessage(email, "lowelowe1998@gmail.com");
        mailService.sendTemplateMessage(email, "minhdat98hy@gmail.com");

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        return new ResponseEntity<>(duration/1000000, HttpStatus.OK);
    }


}
