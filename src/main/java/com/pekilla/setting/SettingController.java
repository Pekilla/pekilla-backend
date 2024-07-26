package com.pekilla.setting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/setting")
@RequiredArgsConstructor
public class SettingController {
    private final SettingService settingService;

    @GetMapping
    public UserSettingDTO getUserSetting() {
        return settingService.getUserSetting();
    }

    @GetMapping("/is-password-valid")
    public boolean isPasswordValid(@RequestParam String password) {
        return settingService.isPasswordValid(password);
    }

    @PatchMapping("/icon")
    public ResponseEntity<?> changeIcon(@RequestBody(required = false) MultipartFile multipartFile, @RequestParam(required = false, defaultValue = "false") boolean isDelete) throws IOException {
        return ResponseEntity.ok(settingService.changeIcon(multipartFile, isDelete));
    }

    @PatchMapping("/banner")
    public ResponseEntity<?> changeBanner(@RequestBody(required = false) MultipartFile multipartFile, @RequestParam(required = false, defaultValue = "false") boolean isDelete) throws IOException {
        settingService.changeBanner(multipartFile, isDelete);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/username")
    public ResponseEntity<?> changeUsername(@RequestParam String username) {
        return settingService.changeUsername(username);
    }

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@RequestParam String password) {
        return settingService.changePassword(password);
    }

    @PatchMapping("/email")
    public ResponseEntity<?> changeEmail(@RequestParam String email) {
        return settingService.changeEmail(email);
    }
}
