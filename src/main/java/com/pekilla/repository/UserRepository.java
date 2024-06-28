package com.pekilla.repository;

import com.pekilla.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepository<User>, JpaRepository<User, Long> {

}