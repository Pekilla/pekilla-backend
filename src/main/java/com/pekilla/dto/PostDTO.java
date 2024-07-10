package com.pekilla.dto;

import com.pekilla.model.Post;
import com.pekilla.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

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

    private Set<String> tags;

    @NotNull
    private String category;

    private Long userId;

    public static PostDTO fromPost(Post post) {
        return PostDTO
                .builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .tags(post.getTags().stream().map(Tag::getContent).collect(Collectors.toSet()))
                    .category(post.getCategory().getName())
                    .userId(post.getOriginalPoster().getId())
                .build();
    }
}
