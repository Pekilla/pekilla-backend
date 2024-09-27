package com.pekilla.category.dto;

import com.pekilla.post.dto.PostViewDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import java.util.Set;

@Builder
public record CategoryViewDTO(
    @Size(min = 3, max = 15)
    String name,

    @Size(min = 10, max = 100)
    String description,

    // DONT TOUCH IT >:(
    @Min(1)
    long creatorId,

    String banner,
    String icon,
    Set<PostViewDTO> posts
) {

}
