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
    public ResponseEntity<PostDTO> getByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

    @Override
    public ResponseEntity<String> create(PostDTO ent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ResponseEntity<String> delete(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ResponseEntity<String> update(long id, PostDTO ent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
