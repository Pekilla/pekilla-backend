package com.pekilla.user;

import com.pekilla.user.dto.FollowUserDTO;
import com.pekilla.user.dto.UserInfoDTO;
import com.pekilla.setting.UserSettingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public UserSettingDTO getUserSetting() {
        return userService.getUserSetting();
    }

    @GetMapping("/followers")
    public ResponseEntity<Set<String>> getFollowers(@PathVariable String username) {
        return ResponseEntity.ok(userService.getFollowers(username));
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

    // NEED TO MOVE THESE two in auth
    @GetMapping("/exists/username")
    public ResponseEntity<?> existsUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.existsUsername(username));
    }

    @GetMapping("/exists/email")
    public ResponseEntity<?> existsEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.existsEmail(email));
    }
}
