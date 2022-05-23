package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.entity.Reservation;
import com.wrfxx.demo10.domain.value.dto.ReservationRequestDTO;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface ReservationService {

    Page<Reservation> getAllReservations(Map<String,Object> filterOption);
    void createReservation(ReservationRequestDTO reservation);
    void deleteReservation(Long reservationId);
    ReservationRequestDTO getReservation(Long reservationId);
}
