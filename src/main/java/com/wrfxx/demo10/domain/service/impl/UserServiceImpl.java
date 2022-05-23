package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.User;
import com.wrfxx.demo10.domain.entity.UserVerification;
import com.wrfxx.demo10.domain.repository.UserRepository;
import com.wrfxx.demo10.domain.repository.UserVerificationRepository;
import com.wrfxx.demo10.domain.service.MailService;
import com.wrfxx.demo10.domain.service.UserService;
import com.wrfxx.demo10.domain.value.dto.DeveloperSignUpDTO;
import com.wrfxx.demo10.domain.value.dto.UserDTO;
import com.wrfxx.demo10.domain.value.dto.CustomerSignUpDTO;
import com.wrfxx.demo10.domain.value.enumurator.ApplicationRoleUser;
import com.wrfxx.demo10.exceptions.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
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
    public void createCustomerUser(CustomerSignUpDTO dto) {
        List<String> errors = new ArrayList<String>();
        if(userRepository.existsByEmail(dto.getEmail())){
            errors.add("Email is already registered");
        }
        if(! errors.isEmpty()){
            throw CustomException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .code("Error in completing registration")
                    .errors(errors)
                    .build();
        }

        try {
            final User savedUser = User
                    .builder()
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .address(dto.getAddress())
                    .phone(dto.getPhone())
                    .authority(ApplicationRoleUser.CUSTOMER)
                    .build();

            // send a verif code to email address

            final User saved = userRepository.save(savedUser);
            String code = UUID.randomUUID().toString();
            mailService.sendEmail(dto.getEmail(),"Acount verification",
                    "Your verifictaion code is "+ code

                            +"Or you can confirm your account by clicking here : "
                            + "http://localhost:8089/verify-account/"+saved.getId()+"/"+code
            );
            userVerificationRepository.save(UserVerification.builder()
                    .code(code)
                    .user(savedUser)
                    .failedAttemps(0L)
                    .build());
        }catch(Exception e){
            throw CustomException.builder()
                    .code("Failed to signup !, error: "+e.getMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
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

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->
                CustomException.builder()
                        .code("Username not found: "+username)
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
        return modelMapper.map(user,UserDetails.class);
    }*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with email "+email));

        return new org.springframework.security.core.userdetails
                .User(email, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
