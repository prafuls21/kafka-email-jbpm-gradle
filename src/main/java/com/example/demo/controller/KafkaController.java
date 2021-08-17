package com.example.demo.controller;


import com.example.demo.model.Mail;
import com.example.demo.service.EmailService;
import com.example.demo.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public final class KafkaController {
    private final ProducerService producerService;

    private static Logger log = LoggerFactory.getLogger(KafkaController.class);
    @Autowired
    private EmailService emailService;

    public KafkaController(ProducerService producerService) {

        this.producerService = producerService;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam String message) {

        System.out.println("Sending Message"+message);
        producerService.sendMessage(message);
    }

   // @GetMapping(path= "/sendEmail", consumes = "application/json", produces = "application/json")
    @GetMapping(path= "/sendEmail")
    public String sendEmail() throws Exception {
        log.info("Spring Mail - Sending Email with Inline Attachment Example");

        Mail mail = new Mail();
        mail.setFrom("prafultest21@gmail.com");
        mail.setTo("prafultest21@gmail.com");
        mail.setSubject("Sending Email with Inline Attachment Example");
        mail.setContent("This tutorial demonstrates how to send an email with inline attachment using Spring Framework.");

        emailService.sendSimpleMessage(mail);
        return "Email sent successfully to "+mail.getTo();
    }

}
