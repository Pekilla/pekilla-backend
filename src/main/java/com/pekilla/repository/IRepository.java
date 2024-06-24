package com.pekilla.repository;

import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IRepository<T, ID> {
    Optional<T> findOneById(ID id);
}
