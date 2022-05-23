package com.wrfxx.demo10.controller;

import com.wrfxx.demo10.domain.entity.User;
import com.wrfxx.demo10.domain.service.impl.AuthService;
import com.wrfxx.demo10.domain.value.dto.CustomerSignUpDTO;
import com.wrfxx.demo10.domain.value.dto.LoginDTO;
import com.wrfxx.demo10.security.JwtUtilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    private JwtUtilityService jwtUtilityService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> userRegistration (@RequestBody CustomerSignUpDTO request) {
        User user = authService.userRegistration(request);

        String token = jwtUtilityService.generateJwtToken(user.getEmail());
        Map<String, Object> response = Collections.singletonMap("jwt-token", token);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public Map<String, Object> login (@RequestBody LoginDTO request) {
        String jwtToken = authService.getJwtTokenAfterUserLogin(request);
        return Collections.singletonMap("token", jwtToken);
    }
}
