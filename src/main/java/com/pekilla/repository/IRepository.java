package com.pekilla.repository;

import java.util.Optional;

public interface IRepository<T, ID> {
    Optional<T> findOneById(ID id);
}
