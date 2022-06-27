package com.wrfxx.demo10.controller;



import com.wrfxx.demo10.domain.entity.Reservation;
import com.wrfxx.demo10.domain.service.ReservationService;
import com.wrfxx.demo10.domain.value.ReservationDTO;
import com.wrfxx.demo10.domain.value.dto.ReservationRequestDTO;
import com.wrfxx.demo10.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;



@RestController
@RequestMapping("api/v1/res")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ReservationRequestDTO getReservation(@PathVariable("id") Long id){
        return reservationService.getReservation(id);
    }
    @GetMapping("/byProjectIdAndUnitNo")
    public ResponseEntity<List<Reservation>> getReservation(
            @RequestParam String unitNo,
            @RequestParam Long projectId,
            Pageable pageable
    ){

        Page<Reservation> page = reservationService.getReservationsByUnitNoAndProjectId(unitNo, projectId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @PostMapping("/create")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationRequestDTO reservation){
        reservationService.createReservation(reservation);
        return new ResponseEntity<ReservationDTO>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteReservation(id);
    }
}
