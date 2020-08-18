package com.mailstarter.controller;

import com.mailstarter.entity.EmailEnvelope;
import com.mailstarter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

/**
 * mailstarter
 * phuc.tranngoc created on 8/13/2020
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendSimpleMessage")
    public ResponseEntity<Void> sendSimpleMessage(@RequestBody EmailEnvelope envelope) {
        emailService.sendSimpleMessage(envelope);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/sendMimeMessage")
    public ResponseEntity<Void> sendMimeMessage(@RequestBody EmailEnvelope envelope) throws MessagingException {
        emailService.sendMimeMessage(envelope);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
