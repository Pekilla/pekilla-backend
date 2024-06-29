package com.pekilla.dto;

import lombok.Builder;

@Builder
public record CommentInfoDTO (
        String message,
        long postId,
        long userId)
{ }
