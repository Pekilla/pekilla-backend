package com.pekilla.user;

import com.pekilla.global.interfaces.IRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends IRepository<User>, JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Deprecated(forRemoval = true)
    @Modifying
    @Query(value = "UPDATE user u SET u.icon = ?2 WHERE u.id = ?1 LIMIT 1", nativeQuery = true)
    int changeIcon(long id, String icon);

    @Deprecated(forRemoval = true)
    @Modifying
    @Query(value = "UPDATE user u SET u.banner = ?2 WHERE u.id = ?1 LIMIT 1", nativeQuery = true)
    int changeBanner(long id, String banner);

    @Deprecated(forRemoval = true)
    @Modifying
    @Query(value = "UPDATE user u SET u.password = ?2 WHERE u.id = ?1 LIMIT 1", nativeQuery = true)
    int changePassword(long id, String password);
}