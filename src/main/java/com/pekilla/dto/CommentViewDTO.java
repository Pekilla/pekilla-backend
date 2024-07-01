package com.pekilla.dto;

import lombok.Builder;

@Builder
public record CommentViewDTO (
        long id,
        String message,
        String username,
        String userLink
) {}
