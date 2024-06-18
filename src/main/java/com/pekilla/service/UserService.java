package com.pekilla.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pekilla.dto.UserDTO;

@Service
public class UserService implements IService<UserDTO>{

    @Override
    public ResponseEntity<UserDTO> getById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public ResponseEntity<UserDTO> getByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

    @Override
    public ResponseEntity<String> create(UserDTO ent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ResponseEntity<String> delete(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ResponseEntity<String> update(long id, UserDTO ent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
