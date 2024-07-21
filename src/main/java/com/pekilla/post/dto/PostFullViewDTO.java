package com.pekilla.post.dto;

import com.pekilla.comment.dto.CommentViewDTO;
import java.util.List;

public record PostFullViewDTO(PostViewDTO post, List<CommentViewDTO> comments) {

}