package com.example.security.services.facades.email;

public interface EmailServiceSender {
    void sendEmail(String toEmail,String subject,String body);
}
