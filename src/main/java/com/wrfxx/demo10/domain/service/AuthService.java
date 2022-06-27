package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.value.dto.*;

public interface AuthService {

    TokenDTO login(LoginDTO dto);
    void createCustomer(CustomerSignUpDTO dto);

    void userVerify(Long userId,String code);
}
