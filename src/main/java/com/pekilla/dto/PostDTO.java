package com.pekilla.dto;

import com.pekilla.enums.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import java.util.List;

@Builder
public record PostDTO(
    Long id,

    @NotBlank
    String title,

    @NotBlank
    String description,

    List<String> tags,

    @NotBlank
    Category category
) {

}
