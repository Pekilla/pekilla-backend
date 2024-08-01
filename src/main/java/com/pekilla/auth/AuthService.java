package com.pekilla.auth;

import com.pekilla.category.exception.InvalidPasswordOrUsername;
import com.pekilla.config.JwtService;
import com.pekilla.upload.FileService;
import com.pekilla.upload.enums.FileType;
import com.pekilla.customer.Customer;
import com.pekilla.customer.CustomerRepository;
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
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final FileService fileService;

    public ResponseEntity<?> login(String username, String password) {
        try {
            Customer customer = customerRepository.findByUsername(username).orElseThrow(InvalidPasswordOrUsername::new);

            if(passwordEncoder.matches(password, customer.getPassword())) {
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        username,
                        password
                    )
                );

                return ResponseEntity.ok(
                    new AuthResponse(username, fileService.getImageUrl(customer.getIcon(), FileType.USER_ICON), jwtService.generateToken(customer))
                );
            }

            else throw new InvalidPasswordOrUsername();
        } catch (InvalidPasswordOrUsername e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    public ResponseEntity<?> signUp(@NotNull String username, @NotNull String password, @NotNull String email) {
        // Need to handle if username already exists

        customerRepository.save(
            Customer.builder()
                .password(passwordEncoder.encode(password))
                .username(username)
                .email(email)
                .build()
        );

        return ResponseEntity.ok("Account successfully created!");
    }

    public boolean validateToken(String token) {
        String username = jwtService.extractUsername(token);
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return jwtService.isTokenValid(token, user);
    }

    public boolean existsUsername(String username) {
        return customerRepository.findByUsername(username).orElse(null) != null;
    }

    public boolean existsEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null) != null;
    }
}