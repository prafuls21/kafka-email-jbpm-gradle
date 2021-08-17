package com.example.demo.service;

import com.example.demo.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(Mail mail) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

     // helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));
        //String inlineImage = "<img src=\"cid:logo.png\"></img><br/>";

        helper.setText( mail.getContent(), true);
        helper.setSubject(mail.getSubject());
        helper.setTo(mail.getTo());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }

}
