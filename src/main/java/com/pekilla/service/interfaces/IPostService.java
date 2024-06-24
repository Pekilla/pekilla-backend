package com.pekilla.service.interfaces;

import com.pekilla.dto.PostDTO;
import com.pekilla.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface IPostService extends IService<PostDTO> {
    Post getPostById(Long id);
    Post getPostByTitle(String title);
}
