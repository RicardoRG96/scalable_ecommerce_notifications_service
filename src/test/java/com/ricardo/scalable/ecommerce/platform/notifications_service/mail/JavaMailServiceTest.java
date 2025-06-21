package com.ricardo.scalable.ecommerce.platform.notifications_service.mail;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model.EmailRequest;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model.EmailTemplateType;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

@SpringBootTest(properties = {
    "management.health.mail.enabled=false"
})
public class JavaMailServiceTest {

    @MockitoBean
    private JavaMailSender mailSender;

    @MockitoBean
    private SpringTemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Test
    void sendEmail_whenCalled_shouldSendEmail() throws Exception {
        EmailRequest emailRequest = new EmailRequest();
        MimeMessage mimeMessage = new MimeMessage((Session) null);

        emailRequest.setTo("test@example.com");
        emailRequest.setTemplateType(EmailTemplateType.USER_REGISTERED);
        emailRequest.setTemplateVariables(Map.of("name", "Test User"));

        when(templateEngine.process(eq("user-registration.html"), any(Context.class)))
            .thenReturn("<html><body>Welcome, Test User!</body></html>");

        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        doNothing().when(mailSender).send(any(MimeMessage.class));
        
        mailService.sendEmail(emailRequest);

        verify(mailSender).send(any(MimeMessage.class));
    }

}
