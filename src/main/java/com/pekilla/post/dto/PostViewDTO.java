package com.pekilla.post.dto;

import com.pekilla.post.Post;
import com.pekilla.tag.Tag;
import lombok.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PostViewDTO extends PostDTO {
    private String username;
    private LocalDateTime addedDate;

    private PostViewDTO(String username, LocalDateTime addedDate, PostDTO postDTO) {
        super(postDTO.getId(), postDTO.getTitle(), postDTO.getDescription(), postDTO.getTags(), postDTO.getCategory(), postDTO.getUserId());
        this.username = username;
        this.addedDate = addedDate;
    }

    public static PostViewDTO fromPost(Post post) {
        return new PostViewDTO(
            post.getOriginalPoster().getUsername(),
            post.getAddedDate(),
            PostDTO.fromPost(post)
        );
    }

    public PostViewDTO(Post post) {
        super(
            post.getId(),
            post.getTitle(),
            post.getDescription(),
            post.getTags().stream().map(Tag::getContent).collect(Collectors.toSet()),
            post.getCategory().getName(),
            post.getOriginalPoster().getId()
        );

        this.username = post.getOriginalPoster().getUsername();
        this.addedDate = post.getAddedDate();
    }
}
