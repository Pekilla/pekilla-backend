package com.pekilla.service.interfaces;

import com.pekilla.dto.PostDTO;
import com.pekilla.model.Post;

public interface IPostService extends IService<PostDTO> {
    Post getPostById(Long id);
    Post getPostByTitle(String title);
}
