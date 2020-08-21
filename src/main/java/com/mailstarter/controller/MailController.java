package com.mailstarter.controller;

import com.mailstarter.config.MailRunable;
import com.mailstarter.entity.MailDto;
import com.mailstarter.service.MailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * mailstarter
 * phuc.tranngoc created on 8/13/2020
 */
@RestController
@RequestMapping("/email")
public class MailController {

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

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
        return new ResponseEntity<>(duration / 1000000, HttpStatus.OK);
    }

    @PostMapping("/sendTemplateMessage")
    public ResponseEntity<?> sendTemplateMessage(@RequestBody MailDto email) throws MessagingException, UnsupportedEncodingException {
        long startTime = System.nanoTime();

//        mailService.sendTemplateMessage(email, "nptran9810@gmail.com");
//        mailService.sendTemplateMessage(email, "nptran9810@gmail.com");
//        mailService.sendTemplateMessage(email, "nptran9810@gmail.com");
//        mailService.sendTemplateMessage(email, "nptran9810@gmail.com");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setAwaitTerminationSeconds(10);

        for (int i = 0; i < 1000; i++) {
            System.out.println(ANSI_BLACK_BACKGROUND + "Sending email " + i + " ...");
            executor.execute(new MailRunable("nptran9810@gmail.com", email));
            System.out.println(ANSI_BLACK_BACKGROUND + "Sent email " + i);
        }

        executor.shutdown();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        return new ResponseEntity<>(duration / 1000000, HttpStatus.OK);
    }


}
