package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.User;
import com.wrfxx.demo10.domain.repository.UserRepository;
import com.wrfxx.demo10.domain.repository.UserVerificationRepository;
import com.wrfxx.demo10.domain.service.MailService;
import com.wrfxx.demo10.domain.service.UserService;
import com.wrfxx.demo10.domain.value.dto.DeveloperSignUpDTO;
import com.wrfxx.demo10.domain.value.dto.UserDTO;
import com.wrfxx.demo10.exceptions.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final MailService mailService;
    private final UserVerificationRepository userVerificationRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, MailService mailService, UserVerificationRepository userVerificationRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.mailService = mailService;
        this.userVerificationRepository = userVerificationRepository;
    }


    @Override
    public Page<User> getAllUsers(Map<String, Object> filterOption) {
        return null;
    }




    @Override
    public void createDeveloperUser(DeveloperSignUpDTO dto) {

    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserDTO getUser(Long userId) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> CustomException.builder()
                        .code("User not found with email: "+email)
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> CustomException.builder()
                        .code("User not found with id: "+id)
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );
    }

    @Override
    public boolean userIsExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }



    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->
                CustomException.builder()
                        .code("Username not found: "+username)
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
        return modelMapper.map(user,UserDetails.class);
    }*/


}
