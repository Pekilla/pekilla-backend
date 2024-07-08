package com.pekilla.repository;

import com.pekilla.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends IRepository<User>, JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}