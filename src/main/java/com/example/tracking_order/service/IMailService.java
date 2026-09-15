package com.example.tracking_order.service;

public interface IMailService {
    void sendMail(String to, String subject, String text);
}
