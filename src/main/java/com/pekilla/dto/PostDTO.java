package com.pekilla.dto;

import com.pekilla.enums.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record PostDTO(
    Long id,

    @NotNull
    String title,

    @NotNull
    String content,

    List<String> tags,

    @NotNull
    Category category
) {

}
