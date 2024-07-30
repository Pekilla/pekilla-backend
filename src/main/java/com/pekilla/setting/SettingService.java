package com.pekilla.setting;

import com.pekilla.config.JwtService;
import com.pekilla.upload.FileService;
import com.pekilla.upload.enums.FileType;
import com.pekilla.user.Customer;
import com.pekilla.user.UserRepository;
import com.pekilla.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SettingService {
    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserSettingDTO getUserSetting() {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return (
            customer != null ? new UserSettingDTO(
                customer.getEmail(),
                customer.getUsername(),
                fileService.getImageUrl(customer.getIcon(), FileType.USER_ICON),
                fileService.getImageUrl(customer.getBanner(), FileType.USER_BANNER)
            ) : null
        );
    }

    public boolean isPasswordValid(String password) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passwordEncoder.matches(password, customer.getPassword());
    }

    public ResponseEntity<?> changeUsername(String username) {
        try {
            Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!customer.getUsername().equals(username)) {
                customer.setUsername(username);

                userRepository.save(customer);
                return ResponseEntity.ok(jwtService.generateToken(customer));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            if (e instanceof DataIntegrityViolationException) return ResponseEntity.status(HttpStatus.CONFLICT).build();
            else if (e instanceof UserNotFoundException) return ResponseEntity.notFound().build();
            else return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> changeEmail(String email) {
        try {
            Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!customer.getEmail().equals(email)) {
                customer.setEmail(email);
                userRepository.save(customer);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            if (e instanceof DataIntegrityViolationException) return ResponseEntity.status(HttpStatus.CONFLICT).build();
            else if (e instanceof UserNotFoundException) return ResponseEntity.notFound().build();
            else return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> changePassword(String password) {
        try {
            Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!passwordEncoder.matches(password, customer.getPassword())) {
                customer.setPassword(passwordEncoder.encode(password));
                userRepository.save(customer);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            if (e instanceof UserNotFoundException) return ResponseEntity.notFound().build();
            else return ResponseEntity.internalServerError().build();
        }
    }

    public String changeIcon(MultipartFile multipartFile, boolean isDelete) throws IOException {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String fileName = isDelete ? null : fileService.saveFile(multipartFile, FileType.USER_ICON);
        customer.setIcon(fileName);
        userRepository.save(customer);
        return fileName != null ? fileService.getImageUrl(fileName, FileType.USER_ICON) : null;
    }

    public void changeBanner(MultipartFile multipartFile, boolean isDelete) throws IOException {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        customer.setBanner(isDelete ? null : fileService.saveFile(multipartFile, FileType.USER_BANNER));
        userRepository.save(customer);
    }
}
