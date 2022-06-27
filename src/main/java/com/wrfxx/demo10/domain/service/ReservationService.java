package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.entity.Reservation;
import com.wrfxx.demo10.domain.value.dto.ReservationRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface ReservationService {

    List<Reservation> getAllReservations();
    Page<Reservation> getReservationsByUnitNoAndProjectId(String unitNo,
                                                          Long projectId,
                                                          Pageable pageable);
    List<Reservation> getReservationsByUser(Long userId);
    void createReservation(ReservationRequestDTO reservation);
    void deleteReservation(Long reservationId);
    ReservationRequestDTO getReservation(Long reservationId);
}
