package com.wrfxx.demo10.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AdElements {

    private String theMainTypeOfAdd;
    private String adSubType;
    private String adDescription;
    private LocalDate advertisementPublicationDate;
    private LocalDate adUpdatedDate;
    private LocalDate adExpirationDate;
    private String adStatus;
    private Long adViews;
}
