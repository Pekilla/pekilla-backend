package com.pekilla.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follower")
public class FollowerController {

    @PatchMapping("/{follower}/follow/{username}")
    public void followUser(@PathVariable String follower, @PathVariable String username) {

    }

    @PatchMapping("/{follower}/unfollow/{username}")
    public void unfollowUser(@PathVariable String follower, @PathVariable String username) {

    }
}
