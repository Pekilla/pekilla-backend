package com.pekilla.dto;

import com.pekilla.model.Post;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PostViewDTO extends PostDTO {
    private String username;
    private String userLink;
    private Date addedDate;

    private PostViewDTO(String username, String userLink, Date addedDate, PostDTO postDTO) {
        super(postDTO.getId(), postDTO.getTitle(), postDTO.getDescription(), postDTO.getTags(), postDTO.getCategory());
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
