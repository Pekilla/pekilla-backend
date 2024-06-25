package com.pekilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IRepository<T> {
    Optional<T> findOneById(long id);
}
