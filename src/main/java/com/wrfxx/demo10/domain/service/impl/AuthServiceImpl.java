package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.User;
import com.wrfxx.demo10.domain.entity.UserVerification;
import com.wrfxx.demo10.domain.repository.UserRepository;
import com.wrfxx.demo10.domain.repository.UserVerificationRepository;
import com.wrfxx.demo10.domain.service.MailService;
import com.wrfxx.demo10.domain.value.dto.CustomerSignUpDTO;
import com.wrfxx.demo10.domain.value.dto.LoginDTO;
import com.wrfxx.demo10.domain.value.dto.UserDTO;
import com.wrfxx.demo10.domain.value.enumurator.ApplicationRoleUser;
import com.wrfxx.demo10.exceptions.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


public class AuthServiceImpl {
    /*private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final MailService mailService;
    private final UserVerificationRepository userVerificationRepository;
    public void createCustomer(CustomerSignUpDTO dto) {

    }

    public UserDTO login(LoginDTO dto) {
        User user = userRepository.findUserByEmail(dto.getEmail()).orElseThrow(
                () -> CustomException.builder()
                        .code("User not found with email: "+dto.getEmail())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );
        if(!user.isActive()){
            throw CustomException.builder()
                    .code("User is not active with id: "+user.getId())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        if(!dto.getPassword().equals(user.getPassword())){
            throw CustomException.builder()
                    .code("Password is incorrect")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();

        }
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with email "+email));

        return new org.springframework.security.core.userdetails
                .User(email, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }*/
    /*public User userRegistration (CustomerSignUpDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return user;
    }*/

    /*public String getJwtTokenAfterUserLogin (LoginDTO request) {
        try {
            Authentication authenticationInput = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            authenticationManager.authenticate(authenticationInput);

            return jwtUtilityService.generateJwtToken(request.getEmail());
        }
        catch (AuthenticationException exception) {
            log.error("User authentication failed, Invalid login credentials..");
            throw new RuntimeException("Invalid login credentials.");
        }
    }*/
}
