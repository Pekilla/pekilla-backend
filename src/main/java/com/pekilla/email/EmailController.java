package com.pekilla.email;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @GetMapping("/welcome-back")
    public void welcomeBack() throws MessagingException {
        emailService.sendHtmlEmail(new String[]{
            "jacksonville@gmail.com",
            "terner@gmail.com",
        }, "Welcome to pekilla pekilla!", "<button>press it</button>");
    }
}
