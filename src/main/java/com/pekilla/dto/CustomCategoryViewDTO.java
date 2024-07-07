package com.pekilla.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CustomCategoryViewDTO(
    @Size(min = 3, max = 15)
    String name,

    @Size(min = 10, max = 100)
    String description,

    @Positive
    long creatorId,

    String banner,
    String icon
) { }
