package com.wrfxx.demo10.domain.value.dto;

import com.wrfxx.demo10.domain.entity.Address;
import com.wrfxx.demo10.domain.entity.Phone;
import com.wrfxx.demo10.domain.entity.Project;
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
public class ReservationRequestDTO {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    @Embedded
    private Phone phone;
    @Embedded
    private Address address;
    private Long jobClassification;
    private String job;
    private Long nationalId;
    @OneToOne
    private Project project;
    private Long unitNumber;
    private Long buildingNumber;
    private String floor;
}
