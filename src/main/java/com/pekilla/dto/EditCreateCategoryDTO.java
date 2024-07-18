package com.pekilla.dto;

import jakarta.validation.constraints.NotNull;

public record EditCreateCategoryDTO(
    @NotNull String name,
    Object banner,
    Object icon,
    @NotNull String description,
    @NotNull long creatorId
) {
}
