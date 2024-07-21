package com.pekilla.user.dto;

import com.pekilla.post.dto.PostViewDTO;
import lombok.Builder;
import java.util.Set;

@Builder
public record UserProfileDTO(
    String username,
    String icon,
    String banner,
    Set<PostViewDTO> posts,
    int commentsNumber,
    int friendNumber
) {
}
