package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.Reservation;
import com.wrfxx.demo10.domain.repository.ReservationRepository;
import com.wrfxx.demo10.domain.service.MailService;
import com.wrfxx.demo10.domain.service.ReservationService;
import com.wrfxx.demo10.domain.value.dto.ReservationRequestDTO;
import com.wrfxx.demo10.exceptions.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
   @Autowired
    private  MailService mailService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRequestRepository, ModelMapper modelMapper, MailService mailService) {
        this.reservationRepository = reservationRequestRepository;
        this.modelMapper = modelMapper;
        this.mailService = mailService;
    }

    @Override
    public List<Reservation> getAllReservations() {

        try{
            return reservationRepository.findAll();
        }catch (Exception e){
            throw CustomException.builder()
                    .code(e.getMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @Override
    public Page<Reservation> getReservationsByUnitNoAndProjectId(String unitNo,
                                                                 Long projectId,
                                                                 Pageable pageable) {
        return reservationRepository.findByUnitNoAndProjectId(unitNo,projectId,pageable);
    }

    @Override
    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationRepository.findAll();
    }

    public ReservationRequestDTO getReservation(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()->
                CustomException.builder()
                        .code("Reservation Not Found with Id: "+reservationId)
                        .status(HttpStatus.NOT_FOUND)
                        .build());
        return modelMapper.map(reservation,ReservationRequestDTO.class);
    }

    @Override
    public void createReservation(ReservationRequestDTO dto) {
        Reservation reservation = modelMapper.map(dto, Reservation.class);
        reservationRepository.save(reservation);
        mailService.sendEmail(
                dto.getUser().getEmail(),
                "Thank you for your request",
                "We will contact you shortly");
    }

    @Override
    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> CustomException.builder()
                        .code("Reservation Not Found with Id: "+reservationId)
                        .status(HttpStatus.NOT_FOUND)
                        .build());
        reservationRepository.delete(reservation);
    }
}
