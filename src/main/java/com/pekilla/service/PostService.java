package com.pekilla.service;

import com.pekilla.model.Post;
import com.pekilla.repository.PostRepository;
import com.pekilla.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pekilla.dto.PostDTO;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public String create(PostDTO ent) {
        return "";
    }

    @Override
    public String delete(long id) {
        postRepository.deleteById(id);
        return "Post deleted successfully";
    }

    @Override
    public String update(long id, PostDTO ent) {
        return "";
    }

    @Override
    public Post getPostById(Long id) {
        return null;
    }

    @Override
    public Post getPostByTitle(String title) {
        return null;
    }
}
