package com.pekilla.dto;

import com.pekilla.model.Post;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PostViewDTO extends PostDTO {
    private String username;
    private String userLink;
    private LocalDateTime addedDate;

    private PostViewDTO(String username, String userLink, LocalDateTime addedDate, PostDTO postDTO) {
        super(postDTO.getId(), postDTO.getTitle(), postDTO.getDescription(), postDTO.getTags(), postDTO.getCategory(), postDTO.getUserId());
        this.username = username;
        this.userLink = userLink;
        this.addedDate = addedDate;
    }

    public static PostViewDTO fromPost(Post post) {
        return new PostViewDTO(
            post.getOriginalPoster().getUsername(),
            post.getOriginalPoster().getLink(),
            post.getAddedDate(),
            PostDTO.fromPost(post)
        );
    }
}
