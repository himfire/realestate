package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.entity.User;
import com.wrfxx.demo10.domain.value.dto.DeveloperSignUpDTO;
import com.wrfxx.demo10.domain.value.dto.UserDTO;
import com.wrfxx.demo10.domain.value.dto.CustomerSignUpDTO;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface UserService {

    Page<User> getAllUsers(Map<String,Object> filterOption);
    void createCustomerUser(CustomerSignUpDTO dto);
    void createDeveloperUser(DeveloperSignUpDTO dto);
    void deleteUser(Long userId);
    UserDTO getUser(Long userId);
}
