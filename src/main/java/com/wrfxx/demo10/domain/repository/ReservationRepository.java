package com.wrfxx.demo10.domain.repository;

import com.wrfxx.demo10.domain.entity.Reservation;
import com.wrfxx.demo10.domain.value.ReservationDTO;
import com.wrfxx.demo10.domain.value.dto.UnitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long>,
        QuerydslPredicateExecutor<Reservation> {
    @Query("select r from Reservation r " +
            "where " +
            "r.unit.unitNo = ?1 " +
            "and r.unit.project.id = ?2"
    )
    Page<Reservation> findByUnitNoAndProjectId(String unitNo, Long projectId, Pageable pageable);
}
