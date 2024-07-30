package com.pekilla.user;

import com.pekilla.comment.CommentRepository;
import com.pekilla.global.interfaces.IService;
import com.pekilla.post.PostRepository;
import com.pekilla.post.dto.PostViewDTO;
import com.pekilla.upload.FileService;
import com.pekilla.upload.enums.FileType;
import com.pekilla.user.dto.FollowUserDTO;
import com.pekilla.user.dto.UserInfoDTO;
import com.pekilla.user.dto.UserProfileDTO;
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
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /**
     * Method to retrieve all post related to a specific user using his username
     *
     * @param username The username of the user
     * @return all posts related to the user with the specified username
     */
    public Set<PostViewDTO> getAllPostsByUsername(String username) {
        return postRepository.findAllByOriginalPosterUsername(username);
    }

    public Customer getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
    }

    public Customer getUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(UserNotFoundException::new);
    }

    public UserProfileDTO getProfile(String username) {
        Customer customer = getUserByUsername(username);
        System.out.println(customer);

        return customer != null ?
            (
                UserProfileDTO
                    .builder()
                    .commentsNumber(commentRepository.countCommentByAuthorId(customer.getId()))
                    .friendNumber(customer.getFollowers().size())
                    .posts(this.getAllPostsByUsername(username))
                    .username(customer.getUsername())
                    .icon(fileService.getImageUrl(customer.getIcon(), FileType.USER_ICON))
                    .banner(fileService.getImageUrl(customer.getBanner(), FileType.USER_BANNER))
                    .build()
            ) : (null);
    }

    public Set<String> getFollowers(String username) {
        return this.getUserByUsername(username).getFollowers()
            .stream().map(Customer::getUsername).collect(Collectors.toSet());
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
        Customer followed = this.getUserByUsername(dto.followed());
        followed.getFollowers().add(this.getUserById(dto.follower()));
        userRepository.save(followed);
    }
}
