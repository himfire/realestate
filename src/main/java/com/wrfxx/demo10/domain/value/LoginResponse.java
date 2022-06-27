package com.wrfxx.demo10.domain.value;

import com.wrfxx.demo10.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private User user;
    private String token;
}
