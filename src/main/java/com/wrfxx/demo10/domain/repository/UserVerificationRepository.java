package com.wrfxx.demo10.domain.repository;

import com.wrfxx.demo10.domain.entity.User;
import com.wrfxx.demo10.domain.entity.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationRepository extends JpaRepository<UserVerification,Long> {
    UserVerification findByUserId(Long userId);
    UserVerification findByUser(User user);
    boolean existsByUserId(Long userId);
    boolean existsByUser(User user);
}
