package com.pekilla.repository;

import com.pekilla.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends IRepository<Category>, JpaRepository<Category, Long> {
    Optional<Category> findOneByName(String name);

    @Query(value = "SELECT EXISTS(SELECT * FROM category c WHERE c.name = ?1)", nativeQuery = true)
    int isExistsByName(String name);
}
