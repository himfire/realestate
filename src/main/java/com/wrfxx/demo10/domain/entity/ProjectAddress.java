package com.wrfxx.demo10.domain.entity;

import com.wrfxx.demo10.domain.value.enumurator.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProjectAddress {

    @Column(name = "Country_Name")
    private Country country;
    @Column(name = "District_Name")
    private String district;
    @Column(name = "City_Name")
    private String city;
    @Column(name = "Neighbourhood_Name")
    private String neighbourhood;
    @Column(name = "Street_Name")
    private String street;
    @Column(name = "Longitude")
    private String longitude;
    @Column(name = "Latitude")
    private String latitude;
}
