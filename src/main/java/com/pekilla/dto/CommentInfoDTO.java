package com.pekilla.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CommentInfoDTO (
        @NotBlank
        String message,

        long postId,
        long userId)
{ }
