package com.pekilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pekilla.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, IRepository<User, Long> {

}