package com.pekilla.controller;

import com.pekilla.dto.UserInfoDTO;
import com.pekilla.dto.UserSettingDTO;
import com.pekilla.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@CrossOrigin("${ALLOWED_URL}")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserInfoByUsername(username));
    }

    @PostMapping("/icon")
    public ResponseEntity<?> changeIcon(@RequestBody(required = false) MultipartFile multipartFile, @RequestParam long userId, @RequestParam(required = false, defaultValue = "false") boolean isDelete) throws IOException {
        userService.changeIcon(multipartFile, userId, isDelete);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/banner")
    public ResponseEntity<?> changeBanner(@RequestBody(required = false) MultipartFile multipartFile, @RequestParam long userId, @RequestParam(required = false, defaultValue = "false") boolean isDelete) throws IOException {
        userService.changeBanner(multipartFile, userId, isDelete);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/setting")
    public UserSettingDTO getUserSetting(@RequestParam long userId) {
        return userService.getUserSetting(userId);
    }
}
