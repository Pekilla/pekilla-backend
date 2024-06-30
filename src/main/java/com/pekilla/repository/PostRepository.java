package com.pekilla.repository;

import com.pekilla.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends IRepository<Post>, JpaRepository<Post, Long> {
    List<Post> findAllByIsActiveTrueOrderByAddedDateDesc();
}
