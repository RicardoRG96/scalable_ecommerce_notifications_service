package com.ricardo.scalable.ecommerce.platform.notifications_service.mail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.MailService;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model.EmailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class JavaMailService implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String fromEmail;

    @Override
    public void sendEmail(EmailRequest request) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(request.getTemplateVariables());

        String html = templateEngine.process(
            request.getTemplateType().getTemplateFileName(),
            thymeleafContext
        );

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(request.getTo());
            helper.setSubject(request.getTemplateType().getSubject());
            helper.setText(html, true);
            helper.setFrom(fromEmail);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

}
