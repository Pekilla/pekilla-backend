package com.pekilla.dto;

import jakarta.validation.constraints.NotBlank;

public record FollowUserDTO(
        @NotBlank
        String follower,

        @NotBlank
        String followed
) { }
