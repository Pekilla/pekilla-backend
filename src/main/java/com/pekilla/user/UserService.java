package com.pekilla.user;

import com.pekilla.global.interfaces.IService;
import com.pekilla.upload.FileService;
import com.pekilla.upload.enums.FileType;
import com.pekilla.user.dto.FollowUserDTO;
import com.pekilla.user.dto.UserInfoDTO;
import com.pekilla.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
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
}
