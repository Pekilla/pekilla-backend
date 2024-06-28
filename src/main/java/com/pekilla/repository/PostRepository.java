package com.pekilla.repository;

import com.pekilla.enums.Category;
import com.pekilla.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends IRepository<Post>, JpaRepository<Post, Long> {
    Optional<Post> findOneByCategoryAndTitle(Category category, String title);
}
