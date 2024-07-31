package com.pekilla.post.dto;

import java.time.LocalDateTime;

/**
 * The exact same DTO as PostViewDto but this DTO is used for the search query because the query
 * is native.
 */
public interface PostViewSqlNativeDto {
    long getId();

    String getTitle();

    String getDescription();

    String[] getTags();

    String getCategory();

    long getUserId();

    String getUsername();

    LocalDateTime getAddedDate();
}