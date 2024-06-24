package com.pekilla.dto;

import com.pekilla.enums.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

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
