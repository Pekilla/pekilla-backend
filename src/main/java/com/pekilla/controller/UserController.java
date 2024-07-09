package com.pekilla.controller;

import com.pekilla.dto.UserSettingDTO;
import com.pekilla.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/icon")
    public void changeIcon(@RequestBody MultipartFile multipartFile, @RequestParam long userId) throws IOException {
        userService.changeIcon(multipartFile, userId);
    }

    @PostMapping("/banner")
    public void changeBanner(@RequestBody MultipartFile multipartFile, @RequestParam long userId) throws IOException {
        userService.changeBanner(multipartFile, userId);
    }

    @GetMapping("/setting")
    public UserSettingDTO getUserSetting(@RequestParam long userId) {
        return userService.getUserSetting(userId);
    }
}
