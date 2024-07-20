package com.pekilla.user;

import com.pekilla.user.dto.FollowUserDTO;
import com.pekilla.user.dto.UserInfoDTO;
import com.pekilla.user.dto.UserSettingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Set;

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

    @GetMapping("/setting")
    public UserSettingDTO getUserSetting(@RequestParam long userId) {
        return userService.getUserSetting(userId);
    }

    @GetMapping("/{userId}/verify-password")
    public boolean isPasswordValid(@PathVariable long userId, @RequestParam String password) {
        return userService.isPasswordValid(userId, password);
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<Set<String>> getFollowers(@PathVariable String username) {
        return ResponseEntity.ok(userService.getFollowers(username));
    }

    @PatchMapping("/icon")
    public ResponseEntity<?> changeIcon(@RequestBody(required = false) MultipartFile multipartFile, @RequestParam long userId, @RequestParam(required = false, defaultValue = "false") boolean isDelete) throws IOException {
        userService.changeIcon(multipartFile, userId, isDelete );
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/banner")
    public ResponseEntity<?> changeBanner(@RequestBody(required = false) MultipartFile multipartFile, @RequestParam long userId, @RequestParam(required = false, defaultValue = "false") boolean isDelete) throws IOException {
        userService.changeBanner(multipartFile, userId, isDelete);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}/username")
    public ResponseEntity<?> changeUsername(@PathVariable long userId, @RequestParam String username) {
        return userService.changeUsername(userId, username);
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<?> changePassword(@PathVariable long userId, @RequestParam String password) {
        return userService.changePassword(userId, password);
    }

    @PatchMapping("/{userId}/email")
    public ResponseEntity<?> changeEmail(@PathVariable long userId, @RequestParam String email) {
        return userService.changeEmail(userId, email);
    }

    @PatchMapping("/follow")
    public ResponseEntity<?> followUser(FollowUserDTO dto) {
        try {
            userService.followUser(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
