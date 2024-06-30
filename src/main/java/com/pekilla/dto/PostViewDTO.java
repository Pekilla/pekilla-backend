package com.pekilla.dto;

import com.pekilla.model.Post;
import com.pekilla.model.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PostViewDTO extends PostDTO {
    private String username;
    private String userLink;

    private PostViewDTO(String username, String userLink, PostDTO postDTO) {
        super(postDTO.getId(), postDTO.getTitle(), postDTO.getDescription(), postDTO.getTags(), postDTO.getCategory());
        this.username = username;
        this.userLink = userLink;
    }

    public static PostViewDTO fromPost(Post post) {
        var postDTO = PostDTO.builder()
            .id(post.getId())
            .title(post.getTitle())
            .description(post.getDescription())
            .tags(post.getTags().stream().map(Tag::getContent).toList())
            .category(post.getCategory())
            .build();

        return new PostViewDTO(
            post.getOriginalPoster().getUsername(),
            post.getOriginalPoster().getLink(),
            postDTO
        );
    }
}
