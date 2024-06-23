package com.pekilla.service.interfaces;

import com.pekilla.dto.UserInfoDTO;
import com.pekilla.model.User;

public interface IUserService extends IService<UserInfoDTO>{

    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User getUserById(Long id);
}
