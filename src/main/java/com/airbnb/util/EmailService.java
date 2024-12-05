package com.airbnb.util;

import com.airbnb.service.WhatsAppService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmailWithAttachment(String to, String subject, String content, File file) {
        try {
            logger.info("sending email "+new Date());
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);

            // Attach file
            FileSystemResource fileResource = new FileSystemResource(file);
            helper.addAttachment(file.getName(), fileResource);

            // Send email
            javaMailSender.send(message);

           logger.info("email sent "+new Date());
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}
