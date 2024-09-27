package com.pekilla.customer;

import com.pekilla.comment.CommentRepository;
import com.pekilla.global.interfaces.IService;
import com.pekilla.post.PostRepository;
import com.pekilla.post.dto.PostViewDTO;
import com.pekilla.upload.FileService;
import com.pekilla.upload.enums.FileType;
import com.pekilla.customer.dto.FollowUserDTO;
import com.pekilla.customer.dto.UserInfoDTO;
import com.pekilla.customer.dto.UserProfileDTO;
import com.pekilla.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class CustomerService implements IService<UserInfoDTO> {
    private final CustomerRepository customerRepository;
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
        return customerRepository.findById(id)
            .orElseThrow(CustomerNotFoundException::new);
    }

    public Customer getUserByUsername(String username) {
        return customerRepository.findByUsername(username)
            .orElseThrow(CustomerNotFoundException::new);
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
        customerRepository.save(followed);
    }
}
