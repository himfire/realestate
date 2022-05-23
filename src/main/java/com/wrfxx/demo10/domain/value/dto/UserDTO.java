package com.wrfxx.demo10.domain.value.dto;

import com.wrfxx.demo10.domain.entity.Address;
import com.wrfxx.demo10.domain.entity.Auditable;
import com.wrfxx.demo10.domain.entity.Phone;
import com.wrfxx.demo10.domain.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends Auditable {

    private Long id;
    private String name;
    private String email;

    @Embedded
    private Phone phone;
    private String password;
    @Embedded
    private Address address;
    private Long jobClassification;
    private String job;
    private Long nationalId;
    private String nationalAddress;
    private Project project;
    private boolean isActive;
}
