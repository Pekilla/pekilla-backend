package com.pekilla.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerService {

    private final UserService userService;

    public void unfollow(String usernameToUnfollow, String followingUsername) {

    }

    public void follow(String usernameToFollow, String followingUsername) {

    }
}
