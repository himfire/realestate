package com.wrfxx.demo10.domain.entity;


import com.wrfxx.demo10.domain.value.enumurator.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private Country country;
    private String state;
    private String city;
    private String address;
}
