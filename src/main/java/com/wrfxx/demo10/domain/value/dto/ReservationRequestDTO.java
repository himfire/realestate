package com.wrfxx.demo10.domain.value.dto;

import com.wrfxx.demo10.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToOne;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationRequestDTO extends Auditable {
    private Long id;
    private boolean isPaid;
    private boolean agreeTerms;

    private ReservationStatus reservationStatus;
    private UserDTO user;
    private UnitDTO unit;
}
