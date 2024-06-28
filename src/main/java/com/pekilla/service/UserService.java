package com.pekilla.service;

import com.pekilla.dto.UserInfoDTO;
import com.pekilla.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IService<UserInfoDTO> {

    public User getUserByEmail(String email) {
        return null;
    }

    public User getUserByUsername(String username) {
        return null;
    }

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
