package com.pekilla.setting;

import com.pekilla.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@CrossOrigin("${ALLOWED_URL}")
@RequestMapping("/api/setting")
@RequiredArgsConstructor
public class SettingController {
    private final UserService userService;

    @GetMapping
    public UserSettingDTO getUserSetting() {
        return userService.getUserSetting();
    }

    @GetMapping("/is-password-valid")
    public boolean isPasswordValid(@RequestParam String password) {
        return userService.isPasswordValid(password);
    }

    @PatchMapping("/icon")
    public ResponseEntity<?> changeIcon(@RequestBody(required = false) MultipartFile multipartFile, @RequestParam(required = false, defaultValue = "false") boolean isDelete) throws IOException {
        userService.changeIcon(multipartFile, isDelete);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/banner")
    public ResponseEntity<?> changeBanner(@RequestBody(required = false) MultipartFile multipartFile, @RequestParam(required = false, defaultValue = "false") boolean isDelete) throws IOException {
        userService.changeBanner(multipartFile, isDelete);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/username")
    public ResponseEntity<?> changeUsername(@RequestParam String username) {
        return userService.changeUsername(username);
    }

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@RequestParam String password) {
        return userService.changePassword(password);
    }

    @PatchMapping("/email")
    public ResponseEntity<?> changeEmail(@RequestParam String email) {
        return userService.changeEmail(email);
    }
}
