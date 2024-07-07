package com.pekilla.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentViewDTO (
        long id,
        String message,
        String username,
        String userLink,
        LocalDateTime addedDate
) {}
