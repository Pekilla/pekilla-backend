package com.pekilla.dto;

import com.pekilla.enums.Category;
import com.pekilla.model.Post;
import com.pekilla.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private List<String> tags;

    @NotNull
    private Category category;

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
