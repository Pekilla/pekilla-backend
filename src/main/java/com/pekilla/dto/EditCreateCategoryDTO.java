package com.pekilla.dto;

import jakarta.validation.constraints.NotNull;

public record EditCreateCategoryDTO(
    @NotNull String name,
    String banner,
    String icon,
    @NotNull String description
) {
}
