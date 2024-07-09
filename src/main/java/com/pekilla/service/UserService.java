package com.pekilla.service;

import com.pekilla.dto.UserInfoDTO;
import com.pekilla.exception.type.UserNotFoundException;
import com.pekilla.model.User;
import com.pekilla.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService implements IService<UserInfoDTO> {

    private final UserRepository userRepository;
    private final FileService fileService;

    public UserService(UserRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }

    public User getUserByEmail(String email) {
        return null;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public String create(UserInfoDTO ent) {
        return "";
    }

    @Override
    public boolean delete(long id) {
        return true;
    }

    @Override
    public String update(long id, UserInfoDTO ent) {
        return "";
    }

    public void changeIcon(MultipartFile multipartFile, long userId) throws IOException {
        userRepository.changeIcon(userId, fileService.saveFile(multipartFile, FileService.FileType.USER_ICON));
    }

    public void changeBanner(MultipartFile multipartFile, long userId) throws IOException {
        userRepository.changeBanner(userId, fileService.saveFile(multipartFile, FileService.FileType.USER_BANNER));
    }
}
