package com.mailstarter.controller;

import com.mailstarter.entity.MailEnvelope;
import com.mailstarter.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> sendSimpleMessage(@RequestBody MailEnvelope envelope) {
        mailService.sendSimpleMessage(envelope);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sendMimeMessage")
    public ResponseEntity<Void> sendMimeMessage(@RequestBody MailEnvelope envelope) throws MessagingException, UnsupportedEncodingException {
        mailService.sendMimeMessage(envelope);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
