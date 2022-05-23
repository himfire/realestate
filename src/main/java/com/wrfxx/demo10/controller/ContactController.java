package com.wrfxx.demo10.controller;

import com.wrfxx.demo10.domain.service.MailService;
import com.wrfxx.demo10.domain.value.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    private MailService mailService;

    @Autowired
    public ContactController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping
    public void sendContactEmail(@RequestBody MailDTO mail){
        mailService.sendEmail(mail.getRecipient(),mail.getTitle(),mail.getText());
    }
}
