package com.c1736.messagingservice.controller;

import com.c1736.messagingservice.entities.EmailDTO;
import com.c1736.messagingservice.service.IEmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class EmailController {
    @Autowired
    IEmailService emailService;

    @PostMapping("sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO email) throws MessagingException {
        emailService.sendMail(email);
        return new ResponseEntity<>("Correo enviado", HttpStatus.OK);
    }

    @PostMapping("sendEmailAlertLogin/{mail}")
    public ResponseEntity<String> sendEmailAlertLogin(@PathVariable String mail) throws MessagingException {
        emailService.sendEmailAlertLogin(mail);
        return new ResponseEntity<>("Correo enviado", HttpStatus.OK);
    }

}
