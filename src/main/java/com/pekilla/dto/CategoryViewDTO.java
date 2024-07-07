package com.pekilla.dto;

import com.pekilla.model.Category;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CategoryViewDTO(
    @Size(min = 3, max = 15)
    String name,

    @Size(min = 10, max = 100)
    String description,

    @Positive
    long creatorId,

    String banner,
    String icon
) {
    public static CategoryViewDTO fromCategory(Category category) {
        return new CategoryViewDTO(
            category.getName(),
            category.getDescription(),
            category.getCreator().getId(),
            category.getBanner(),
            category.getIcon()
        );
    }

}
