package com.pekilla.repository;

import com.pekilla.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends IRepository<Post>, JpaRepository<Post, Long> {
    List<Post> findAllByIsActiveTrueOrderByLastModifiedDateDesc();

    @Query(value = """
    SELECT * FROM post p WHERE p.is_active = 1
    AND (?1 = '' OR ?1 = p.category) AND (?2 = '' OR UPPER(p.title) LIKE %?2% OR UPPER(p.description) LIKE %?2%)
    ORDER BY last_modified_date DESC
    """, nativeQuery = true)
    List<Post> searchPosts(String category, String content);
}
