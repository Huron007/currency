package com.kodilla.currency.service;

import com.kodilla.currency.config.AdminConfig;
import com.kodilla.currency.config.CompanyConfig;
import com.kodilla.currency.entity.Alert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailCreatorService {

    private final AdminConfig adminConfig;
    private final CompanyConfig companyConfig;
    @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;

    public String buildAlertMessage(Alert alert) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can check exchange rate of most used currencies.");
        functionality.add("Application allows you to calculate value of currencies.");
        functionality.add("You can set up alerts and get notification by mail when exchange rate of currency passes certain margin.");

        Context context = new Context();
        context.setVariable("message", "Currency " + alert.getCode() + " has passed " + alert.getTrackedMargin() + " exchange rate margin.");
        context.setVariable("tasks_url", "...");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("preview_message", alert.getCode() + " exchange rate alert.");
        context.setVariable("goodbye_message", "Have a nice day " + adminConfig.getAdminName());
        context.setVariable("company_details", companyConfig.getCompanyName() + " " + adminConfig.getAdminMail());
        context.setVariable("show_button", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/alert-triggered-mail", context);
    }
}
