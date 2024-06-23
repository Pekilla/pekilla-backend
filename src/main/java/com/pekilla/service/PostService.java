package com.pekilla.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pekilla.dto.PostDTO;

@Service
public class PostService implements IService<PostDTO> {

    @Override
    public ResponseEntity<PostDTO> getById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }


    @Override
    public PostDTO getByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

    @Override
    public String create(PostDTO ent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public String delete(long id) {
        return "Post deleted successfully";
    }

    @Override
    public String update(long id, PostDTO ent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
