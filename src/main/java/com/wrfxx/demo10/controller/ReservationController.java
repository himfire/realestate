package com.wrfxx.demo10.controller;

import com.wrfxx.demo10.domain.service.ReservationService;
import com.wrfxx.demo10.domain.value.dto.ReservationRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reservation")
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ReservationRequestDTO getReservation(@PathVariable("id") Long id){
        return reservationService.getReservation(id);
    }
    @PostMapping("/create")
    /*public Long createReservation(@RequestBody ReservationRequestDTO reservation){
        return reservationService.createReservation(reservation);
    }*/

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteReservation(id);
    }
}
