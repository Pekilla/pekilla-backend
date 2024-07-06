package com.pekilla.repository;

import com.pekilla.model.CustomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomCategoryRepository extends IRepository<CustomCategory>, JpaRepository<CustomCategory, Long> {

    Optional<CustomCategory> findByName(String name);

}
