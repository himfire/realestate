package com.wrfxx.demo10.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Reservation extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isPaid;
    private boolean agreeTerms;
    @OneToOne
    private ReservationStatus reservationStatus;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnoreProperties({"project","facilities" ,"description","unitClassification","unitType","unitImagesURL"})
    @ManyToOne(fetch = FetchType.EAGER)
    private Unit unit;
}
