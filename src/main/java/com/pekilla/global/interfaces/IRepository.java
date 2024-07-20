package com.pekilla.global.interfaces;

import java.util.Optional;

public interface IRepository<T> {
    Optional<T> findOneById(long id);
}
