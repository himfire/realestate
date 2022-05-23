package com.wrfxx.demo10.domain.value.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSignUpDTO {
    private String name;
    private String email;
    private String username;
    @Embedded
    private String phone;
    private String password;
    private String address;
}
