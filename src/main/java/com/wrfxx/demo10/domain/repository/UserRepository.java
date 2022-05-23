package com.wrfxx.demo10.domain.repository;

import com.wrfxx.demo10.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, QuerydslPredicateExecutor<User> {
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long id);
}
