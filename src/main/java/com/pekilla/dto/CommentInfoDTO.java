package com.pekilla.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentInfoDTO {

    private String message;
    private long postId;
    private long userId;
}
