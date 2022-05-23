package com.wrfxx.demo10.domain.value.dto;

import com.wrfxx.demo10.domain.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperSignUpDTO {
    private String name;
    private String email;
    @Embedded
    private Phone phone;
    private String password;
}
