package com.pekilla.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateUpdateCommentDTO(
        @NotBlank
        @Size(min = 3, max = 1000)
        String message,

        @Min(1)
        long postId,
        @Min(1)
        long userId)
{ }
