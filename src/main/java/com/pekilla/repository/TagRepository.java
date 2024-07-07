package com.pekilla.repository;

import com.pekilla.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TagRepository extends IRepository<Tag>, JpaRepository<Tag, Long> {
    Optional<Tag> findOneByContent(String content);
}
