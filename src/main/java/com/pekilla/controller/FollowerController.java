package com.pekilla.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follower")
public class FollowerController {

    @PostMapping("")
    public void followUser() {

    }

    @PostMapping("")
    public void unfollowUser(String userFollowedName, String followingUserName) {

    }
}
