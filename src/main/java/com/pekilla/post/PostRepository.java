package com.pekilla.post;

import com.pekilla.global.interfaces.IRepository;
import com.pekilla.post.dto.PostViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends IRepository<Post>, JpaRepository<Post, Long> {
    List<Post> findAllByIsActiveTrueOrderByLastModifiedDateDesc();

    @Query(value = """
    SELECT post.* FROM post
    JOIN category ON post.category_id = category.id
    WHERE post.is_active = true
    AND (?1 = '' OR ?1 = category.name)
    AND (?2 = '' OR (post.title ILIKE CONCAT('%', ?2, '%') OR post.description ILIKE CONCAT('%', ?2, '%')))
    AND (?4 = 0 OR array(SELECT t.content FROM tag t JOIN rel_post_tag rpt ON rpt.tag_id = t.id AND rpt.post_id = post.id) @> ?3)
    ORDER BY post.last_modified_date DESC
    """, nativeQuery = true)
    List<Post> searchPosts(String category, String content, String[] tags, int tagsLength);

    @Query(value = "SELECT array[1, 2, 3, 4] @> ?1", nativeQuery = true)
    boolean countPosts(Integer[] random);

    @Query("SELECT new com.pekilla.post.dto.PostViewDTO(p) FROM Post p WHERE p.originalPoster.username = ?1")
    Set<PostViewDTO> findAllByOriginalPosterUsername(String username);

    @Query("SELECT new com.pekilla.post.dto.PostViewDTO(p) FROM Post p WHERE p.category.name = ?1 and p.isActive = true")
    Set<PostViewDTO> findAllByCategoryAndIsActiveTrue(String category);
}
