package com.pekilla.service;

import com.pekilla.dto.UserInfoDTO;
import com.pekilla.dto.UserSettingDTO;
import com.pekilla.enums.FileType;
import com.pekilla.exception.type.UserNotFoundException;
import com.pekilla.model.User;
import com.pekilla.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class UserService implements IService<UserInfoDTO> {

    private final UserRepository userRepository;
    private final FileService fileService;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }


    public UserInfoDTO getUserInfoByUsername(String username) {
        User user = getUserByUsername(username);
        return UserInfoDTO
                .builder()
                    .username(user.getUsername())
                    .icon(fileService.getImageUrl(user.getIcon(), FileType.USER_ICON))
                    .banner(fileService.getImageUrl(user.getBanner(), FileType.USER_BANNER))
                .build();
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

    public void changeIcon(MultipartFile multipartFile, long userId, boolean isDelete) throws IOException {
        userRepository.changeIcon(userId, isDelete ? null : fileService.saveFile(multipartFile, FileType.USER_ICON));
    }

    public void changeBanner(MultipartFile multipartFile, long userId, boolean isDelete) throws IOException {
        userRepository.changeBanner(userId, isDelete ? null : fileService.saveFile(multipartFile, FileType.USER_BANNER));
    }

    public UserSettingDTO getUserSetting(long userId) {
        User user = this.getUserById(userId);
        return (
            user != null ? new UserSettingDTO(
                user.getEmail(),
                user.getUsername(),
                fileService.getImageUrl(user.getIcon(), FileType.USER_ICON),
                fileService.getImageUrl(user.getBanner(), FileType.USER_BANNER)
            ) : null
        );
    }

    public boolean isPasswordValid(long userId, String password) {
        return userRepository.passwordAndUsername(userId, password) == 1;
    }
}
