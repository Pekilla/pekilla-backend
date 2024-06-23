package com.pekilla.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pekilla.dto.UserInfoDTO;

@Service
public class UserService implements IService<UserInfoDTO>{

    @Override
    public ResponseEntity<UserInfoDTO> getById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public UserInfoDTO getByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

    @Override
    public String create(UserInfoDTO ent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public String delete(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public String update(long id, UserInfoDTO ent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    
}
