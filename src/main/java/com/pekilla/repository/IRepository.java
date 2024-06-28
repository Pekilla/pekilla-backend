package com.pekilla.repository;

import java.util.Optional;

public interface IRepository<T> {
    Optional<T> findOneById(long id);
}
