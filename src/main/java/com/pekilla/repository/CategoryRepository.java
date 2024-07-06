package com.pekilla.repository;

import com.pekilla.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends IRepository<Category>, JpaRepository<Category, Long> {

}
