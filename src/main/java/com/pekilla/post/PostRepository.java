package com.pekilla.post;

import com.pekilla.global.interfaces.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends IRepository<Post>, JpaRepository<Post, Long> {
    List<Post> findAllByIsActiveTrueOrderByLastModifiedDateDesc();

    @Query(value = """
    SELECT p FROM Post p WHERE p.isActive = true
    AND (?1 = '' OR ?1 = p.category.name) AND (?2 = '' OR UPPER(p.title) LIKE %?2% OR UPPER(p.description) LIKE %?2%)
    ORDER BY p.lastModifiedDate DESC
    """)
    List<Post> searchPosts(String category, String content);
}
