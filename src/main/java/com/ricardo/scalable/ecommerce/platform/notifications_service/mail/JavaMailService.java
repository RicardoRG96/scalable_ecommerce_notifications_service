package com.ricardo.scalable.ecommerce.platform.notifications_service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.dto.EmailRequest;

@Service
public class JavaMailService implements MailService {

    @Autowired
    private final JavaMailSender mailSender;

    public JavaMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());
        message.setFrom("ricardoretamal10@gmail.com");

        mailSender.send(message);
    }



}
