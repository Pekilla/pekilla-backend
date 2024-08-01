package com.pekilla.customer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record FollowUserDTO(
        @Min(1)
        long follower,

        @NotBlank
        String followed
) { }
