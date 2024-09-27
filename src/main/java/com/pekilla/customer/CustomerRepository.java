package com.pekilla.customer;

import com.pekilla.global.interfaces.IRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface CustomerRepository extends IRepository<Customer>, JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByEmail(String email);
}