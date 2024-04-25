package com.c1736.messagingservice.service;


import com.c1736.messagingservice.entities.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {
    public void sendMail(EmailDTO emailDTO) throws MessagingException;
    public void sendEmailAlertLogin(String mail) throws MessagingException;

}
