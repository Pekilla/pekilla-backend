package com.pekilla.user;

import com.pekilla.global.interfaces.IService;
import com.pekilla.upload.FileService;
import com.pekilla.upload.enums.FileType;
import com.pekilla.user.dto.FollowUserDTO;
import com.pekilla.user.dto.UserInfoDTO;
import com.pekilla.setting.UserSettingDTO;
import com.pekilla.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class UserService implements IService<UserInfoDTO> {
    private final UserRepository userRepository;

    private final FileService fileService;
    private final PasswordEncoder passwordEncoder;

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

    public Set<String> getFollowers(String username) {
        return this.getUserByUsername(username).getFollowers()
            .stream().map(User::getUsername).collect(Collectors.toSet());
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

    public void followUser(FollowUserDTO dto) {
        User followed = this.getUserByUsername(dto.followed());
        followed.getFollowers().add(this.getUserById(dto.follower()));
        userRepository.save(followed);
    }

    public void changeIcon(MultipartFile multipartFile, boolean isDelete) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setIcon(isDelete ? null : fileService.saveFile(multipartFile, FileType.USER_ICON));
        userRepository.save(user);
    }

    public void changeBanner(MultipartFile multipartFile, boolean isDelete) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setBanner(isDelete ? null : fileService.saveFile(multipartFile, FileType.USER_BANNER));
        userRepository.save(user);
    }

    public UserSettingDTO getUserSetting() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return (
            user != null ? new UserSettingDTO(
                user.getEmail(),
                user.getUsername(),
                fileService.getImageUrl(user.getIcon(), FileType.USER_ICON),
                fileService.getImageUrl(user.getBanner(), FileType.USER_BANNER)
            ) : null
        );
    }

    public boolean isPasswordValid(String password) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passwordEncoder.matches(password, user.getPassword());
    }

    public ResponseEntity<?> changeUsername(String username) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!user.getUsername().equals(username)) {
                user.setUsername(username);

                userRepository.save(user);
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            if (e instanceof DataIntegrityViolationException) return ResponseEntity.status(HttpStatus.CONFLICT).build();
            else if (e instanceof UserNotFoundException) return ResponseEntity.notFound().build();
            else return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> changeEmail(String email) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!user.getEmail().equals(email)) {
                user.setEmail(email);
                userRepository.save(user);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            if (e instanceof DataIntegrityViolationException) return ResponseEntity.status(HttpStatus.CONFLICT).build();
            else if (e instanceof UserNotFoundException) return ResponseEntity.notFound().build();
            else return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> changePassword(String password) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!passwordEncoder.matches(password, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            if (e instanceof UserNotFoundException) return ResponseEntity.notFound().build();
            else return ResponseEntity.internalServerError().build();
        }
    }
}
