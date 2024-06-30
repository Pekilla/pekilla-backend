package com.pekilla.dto;

import lombok.Builder;

@Builder
public record CommentViewDTO (
        String message,
        String username,
        String userLink
) {}
