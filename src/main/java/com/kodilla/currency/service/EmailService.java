package com.kodilla.currency.service;

import com.kodilla.currency.config.AdminConfig;
import com.kodilla.currency.entity.Alert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final MailCreatorService mailCreatorService;
    private final AdminConfig adminConfig;

    public void send(final Alert alert){
        javaMailSender.send(createMimeMessage(alert));
    }

    public MimeMessagePreparator createMimeMessage(final Alert alert) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(adminConfig.getAdminMail());
            messageHelper.setSubject(alert.getCode() + " has reached tracked margin.");
            messageHelper.setText(mailCreatorService.buildAlertMessage(alert), true);
        };
    }
}
