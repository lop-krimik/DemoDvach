package com.lopkrimik.notification_service.service;

import com.domain.basedomain.events.PasteEvent;
import com.domain.basedomain.events.UserEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @PostConstruct
    public void init() {
        System.out.println("emailFrom: " + emailFrom);  // Отладочный вывод
    }
    public void sendPasteNotification (PasteEvent pasteEvent){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(pasteEvent.getEmail());
        message.setText(String.format("Your paste with following text:\n \"%s\" \n Has been created successfully",pasteEvent.getText()));
        message.setSubject(String.format("Paste: %s", pasteEvent.getTitle()));
        mailSender.send(message);
    }

    public void sendUserNotification (UserEvent userEvent){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(userEvent.getEmail());
        message.setText("Thanks for using pastebin");
        message.setSubject(String.format("Hello %s", userEvent.getUsername()));
        mailSender.send(message);

    }

    }

