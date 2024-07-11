package com.pekilla.repository;

import com.pekilla.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends IRepository<User>, JpaRepository<User, Long> {
    @Modifying
    @Query(value = "UPDATE user u SET u.icon = ?2 WHERE u.id = ?1 LIMIT 1", nativeQuery = true)
    int changeIcon(long id, String icon);

    Optional<User> findByUsername(String username);
    
    @Modifying
    @Query(value = "UPDATE user u SET u.banner = ?2 WHERE u.id = ?1 LIMIT 1", nativeQuery = true)
    int changeBanner(long id, String banner);

    @Query(value = "SELECT EXISTS(SELECT * FROM user u WHERE u.id = ?1 AND u.password = ?2 LIMIT 1)", nativeQuery = true)
    int passwordAndUsername(long id, String password);

    @Modifying
    @Query(value = "UPDATE user u SET u.password = ?2 WHERE u.id = ?1 LIMIT 1", nativeQuery = true)
    int changePassword(long id, String password);
}