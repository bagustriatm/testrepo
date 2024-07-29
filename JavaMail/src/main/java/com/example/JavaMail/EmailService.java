package com.example.JavaMail;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    @Autowired
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public boolean sendEmail(String to, String subject, String body, String attachmentPath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setFrom("bagustri734@gmail.com");
            helper.setSubject(subject);
            helper.setText(body);

            if (attachmentPath != null && !attachmentPath.isEmpty()) {
                File file = new File(attachmentPath);
                helper.addAttachment(file.getName(), file);
            }

            mailSender.send(message);
            return true;

        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
