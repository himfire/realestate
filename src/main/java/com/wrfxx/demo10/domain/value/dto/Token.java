package com.wrfxx.demo10.domain.value.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    private Long userId;
    private String token;
    private UserDTO dto;
}
