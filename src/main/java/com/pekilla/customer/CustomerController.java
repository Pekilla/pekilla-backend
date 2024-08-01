package com.pekilla.customer;

import com.pekilla.customer.dto.FollowUserDTO;
import com.pekilla.customer.dto.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{username}/profile")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable String username) {
        return ResponseEntity.ok(customerService.getProfile(username));
    }

    @GetMapping("/followers")
    public ResponseEntity<Set<String>> getFollowers(@RequestParam String username) {
        return ResponseEntity.ok(customerService.getFollowers(username));
    }

    @PatchMapping("/follow")
    public ResponseEntity<?> followUser(FollowUserDTO dto) {
        try {
            customerService.followUser(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
