package com.pekilla.dto;

import com.pekilla.enums.Category;
import com.pekilla.model.Post;
import com.pekilla.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    Category category
) {
    public static PostDTO fromPost(Post post) {
        return PostDTO.builder()
            .id(post.getId())
            .title(post.getTitle())
            .description(post.getDescription())
            .tags(post.getTags().stream().map(Tag::getContent).toList())
            .category(post.getCategory())
            .build();
    }
}
