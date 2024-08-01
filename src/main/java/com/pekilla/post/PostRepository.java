package com.pekilla.post;

import com.pekilla.category.Category;
import com.pekilla.global.interfaces.IRepository;
import com.pekilla.post.dto.PostViewDTO;
import com.pekilla.post.dto.PostViewSqlNativeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends IRepository<Post>, JpaRepository<Post, Long> {
    @Query("SELECT new com.pekilla.post.dto.PostViewDTO(p) FROM Post p WHERE p.isActive = true ORDER BY p.addedDate DESC")
    Page<PostViewDTO> findAllByIsActiveTrueOrderByAddedDateDesc(Pageable pageable);

    /**
     * The query to get the tags of an ad is duplicated because I didn't find a
     * way to set variable directly into a query in postgres.
     */
    @Query(value = """
    SELECT
        post.id AS id,
        post.title AS title,
        post.description AS description,
        array(SELECT t.content FROM tag t JOIN rel_post_tag rpt ON rpt.tag_id = t.id AND rpt.post_id = post.id) AS tags,
        category.name as category,
        customer.id as userId,
        customer.username as username,
        post.added_date AS addedDate
    FROM post
    JOIN category ON post.category_id = category.id
    JOIN customer ON post.original_poster_id = customer.id
    WHERE post.is_active = true
    AND (:#{#content.empty} OR (POSITION(:content IN LOWER(post.title)) > 0 OR POSITION(:content IN LOWER(post.description)) > 0))
    AND (:#{#category.empty} OR category.name = :category)
    AND (:#{#tags.length} = 0 OR array(SELECT t.content FROM tag t JOIN rel_post_tag rpt ON rpt.tag_id = t.id AND rpt.post_id = post.id) @> :tags)
    ORDER BY post.added_date DESC
    """, nativeQuery = true)
    Page<PostViewSqlNativeDto> searchPostsByAllCriteria(@Param("category") String category, @Param("content") String content, @Param("tags") String[] tags, Pageable pageable);

    @Query("""
    SELECT new com.pekilla.post.dto.PostViewDTO(p) FROM Post p
    WHERE p.isActive = true
    AND p.category.id = :categoryId
    ORDER BY p.addedDate DESC
    """)
    Page<PostViewDTO> searchPostByCategoryId(@Param("categoryId") long categoryId, Pageable pageable);

    @Query("""
    SELECT new com.pekilla.post.dto.PostViewDTO(p) FROM Post p
    WHERE p.isActive = true
    AND (p.title ILIKE %:content% OR p.description ILIKE %:content%)
    ORDER BY p.addedDate DESC
    """)
    Page<PostViewDTO> searchPostByContent(@Param("content") String content, Pageable pageable);

    @Query(value = """
    SELECT
        post.id AS id,
        post.title AS title,
        post.description AS description,
        array(SELECT t.content FROM tag t JOIN rel_post_tag rpt ON rpt.tag_id = t.id AND rpt.post_id = post.id) AS tags,
        category.name as category,
        customer.id as userId,
        customer.username as username,
        post.added_date AS addedDate
    FROM post
    JOIN category ON post.category_id = category.id
    JOIN customer ON post.original_poster_id = customer.id
    WHERE post.is_active = true
    AND array(SELECT t.content FROM tag t JOIN rel_post_tag rpt ON rpt.tag_id = t.id AND rpt.post_id = post.id) @> :tags
    ORDER BY post.added_date DESC
    """, nativeQuery = true)
    Page<PostViewDTO> searchPostByTags(@Param("tags") String[] tags, Pageable pageable);

    @Query("SELECT new com.pekilla.post.dto.PostViewDTO(p) FROM Post p WHERE p.originalPoster.username = ?1")
    Set<PostViewDTO> findAllByOriginalPosterUsername(String username);

    @Query("SELECT new com.pekilla.post.dto.PostViewDTO(p) FROM Post p WHERE p.category.name = ?1 and p.isActive = true")
    Set<PostViewDTO> findAllByCategoryAndIsActiveTrue(String category);
}
