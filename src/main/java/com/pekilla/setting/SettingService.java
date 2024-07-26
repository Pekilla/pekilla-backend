package com.pekilla.setting;

import com.pekilla.config.JwtService;
import com.pekilla.upload.FileService;
import com.pekilla.upload.enums.FileType;
import com.pekilla.user.User;
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return (
            user != null ? new UserSettingDTO(
                user.getEmail(),
                user.getUsername(),
                fileService.getImageUrl(user.getIcon(), FileType.USER_ICON),
                fileService.getImageUrl(user.getBanner(), FileType.USER_BANNER)
            ) : null
        );
    }

    public boolean isPasswordValid(String password) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passwordEncoder.matches(password, user.getPassword());
    }

    public ResponseEntity<?> changeUsername(String username) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!user.getUsername().equals(username)) {
                user.setUsername(username);

                userRepository.save(user);
                return ResponseEntity.ok(jwtService.generateToken(user));
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
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!user.getEmail().equals(email)) {
                user.setEmail(email);
                userRepository.save(user);
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
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!passwordEncoder.matches(password, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            if (e instanceof UserNotFoundException) return ResponseEntity.notFound().build();
            else return ResponseEntity.internalServerError().build();
        }
    }

    public String changeIcon(MultipartFile multipartFile, boolean isDelete) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String fileName = isDelete ? null : fileService.saveFile(multipartFile, FileType.USER_ICON);
        user.setIcon(fileName);
        userRepository.save(user);
        return fileName != null ? fileService.getImageUrl(fileName, FileType.USER_ICON) : null;
    }

    public void changeBanner(MultipartFile multipartFile, boolean isDelete) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setBanner(isDelete ? null : fileService.saveFile(multipartFile, FileType.USER_BANNER));
        userRepository.save(user);
    }
}
