package com.pekilla.auth;

import com.pekilla.email.EmailService;
import io.jsonwebtoken.JwtException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/validate-token")
    public boolean validateToken(@RequestParam String token) {
        try {
            return authService.validateToken(token);
        } catch (JwtException e) {
            return false;
        }
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam String email
    ) {
        return authService.signUp(username, password, email);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        System.out.println(username+" and password : "+password);
        return authService.login(username, password);
    }

    @GetMapping("/exists/username")
    public ResponseEntity<?> existsUsername(@RequestParam String username) {
        return ResponseEntity.ok(authService.existsUsername(username));
    }

    @GetMapping("/exists/email")
    public ResponseEntity<?> existsEmail(@RequestParam String email) {
        return ResponseEntity.ok(authService.existsEmail(email));
    }

    private final EmailService emailService;

    @GetMapping("/welcome-back")
    public void welcomeBack() throws MessagingException {
        emailService.sendHtmlEmail(new String[]{
            "jacksonville@gmail.com",
            "terner@gmail.com",
        }, "Welcome to pekilla pekilla!", "<button>press it</button>");
    }
}