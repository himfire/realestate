package com.wrfxx.demo10.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProjectInternal {

    private String furnished;
    private String kitchen;
    private String airCondition;
    private String facilities;
}
