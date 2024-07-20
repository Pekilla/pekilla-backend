package com.pekilla.auth;

import com.pekilla.category.exception.InvalidPasswordOrUsername;
import com.pekilla.config.JwtService;
import com.pekilla.user.User;
import com.pekilla.user.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public ResponseEntity<?> login(String username, String password) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(InvalidPasswordOrUsername::new);

            if(passwordEncoder.matches(password, user.getPassword())) {
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        username,
                        password
                    )
                );

                return ResponseEntity.ok(
                    new AuthResponse(jwtService.generateToken(user))
                );
            }

            else throw new InvalidPasswordOrUsername();
        } catch (InvalidPasswordOrUsername e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    public String signUp(@NotNull String username, @NotNull String password, @NotNull String email) {
        User user = userRepository.save(
            User.builder()
                .password(passwordEncoder.encode(password))
                .username(username)
                .email(email)
                .build()
        );

        return jwtService.generateToken(user);
    }

    public boolean validateToken(String token) {
        String username = jwtService.extractUsername(token);
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return jwtService.isTokenValid(token, user);
    }

    public boolean existsUsername(String username) {
        return userRepository.findByUsername(username).orElse(null) != null;
    }

    public boolean existsEmail(String email) {
        return userRepository.findByEmail(email).orElse(null) != null;
    }
}