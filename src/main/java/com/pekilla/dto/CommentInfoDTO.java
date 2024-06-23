package com.pekilla.dto;

import lombok.Builder;

@Builder
public class CommentInfoDTO {

    private String message;
    private long postId;
    private long userId;
}
