package com.pekilla.service;

import com.pekilla.model.User;
import com.pekilla.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import com.pekilla.dto.UserInfoDTO;

@Service
public class UserService implements IUserService {


    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public String create(UserInfoDTO ent) {
        return "";
    }

    @Override
    public String delete(long id) {
        return "";
    }

    @Override
    public String update(long id, UserInfoDTO ent) {
        return "";
    }
}
