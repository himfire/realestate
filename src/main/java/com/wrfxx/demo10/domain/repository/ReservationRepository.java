package com.wrfxx.demo10.domain.repository;

import com.wrfxx.demo10.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReservationRepository extends JpaRepository<Reservation,Long>, QuerydslPredicateExecutor<Reservation> {
}
