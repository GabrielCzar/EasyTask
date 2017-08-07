package com.easytask.service.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendRecoveryEmail(String email, String token) {
        Runnable email_recovery = () -> {
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            final Context context = new Context();
            final String content;

            context.setVariable("token", token);
            context.setVariable("link", "localhost:8080");
            context.setVariable("email", email);
            content =  templateEngine.process("mail/recovery-password", context);

            try {
                message.setTo(email);
                message.setFrom("no-reply@easytask.com");
                message.setSubject("EasyTask - Recovery Password");
                message.setText(content, true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            mailSender.send(mimeMessage);

        };
        Thread thread = new Thread(email_recovery);
        thread.start();
    }
}
