package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.config.TokenProvider;
import com.wrfxx.demo10.domain.entity.User;
import com.wrfxx.demo10.domain.entity.UserVerification;
import com.wrfxx.demo10.domain.repository.UserVerificationRepository;
import com.wrfxx.demo10.domain.service.AuthService;
import com.wrfxx.demo10.domain.service.MailService;
import com.wrfxx.demo10.domain.service.UserService;
import com.wrfxx.demo10.domain.value.UserPrincipal;
import com.wrfxx.demo10.domain.value.dto.*;
import com.wrfxx.demo10.domain.value.enumurator.ApplicationRoleUser;
import com.wrfxx.demo10.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
public class AuthServiceImplementation implements AuthService, UserDetailsService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Lazy
    private final TokenProvider tokenProvider;
    private final MailService mailService;
    private final UserVerificationRepository userVerificationRepository;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImplementation(@Lazy UserService userService, PasswordEncoder passwordEncoder, ModelMapper modelMapper, TokenProvider tokenProvider, MailService mailService, UserVerificationRepository userVerificationRepository, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.tokenProvider = tokenProvider;
        this.mailService = mailService;
        this.userVerificationRepository = userVerificationRepository;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        System.out.println("user password is: "+user.getPassword());
        UserPrincipal userPrincipal = new UserPrincipal(user);
        userPrincipal.setAuthorities(List.of(new SimpleGrantedAuthority(user.getAuthority().toString())));
        return userPrincipal;
    }
/*    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        System.out.println("Email: "+email);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                        new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

    }*/

/*    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);

        return new org.springframework.security.core.userdetails
                .User(email, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }*/

    @Override
    public TokenDTO login(LoginDTO dto) {

        try {
            User user = userService.getUserByEmail(dto.getEmail());
            // user = getUserByUsername(dto.getLogin());
            if ( ! user.isActive() ){

                throw CustomException.builder()
                        .code("Account disabled !")
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), dto.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info(String.format("Authentication success for user { login: %s } at %s",dto.getEmail(), LocalDateTime.now().toString()));
            return new TokenDTO(tokenProvider.generateToken(authentication));
        }catch (Exception e){
            throw CustomException.builder()
                    .code("Bad credentials !")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
    @Override
    public void userVerify(Long userId, String code) {
        User user = userService.getUserById(userId);
        UserVerification userVerification = userVerificationRepository.findByUserId(user.getId());
        userVerificationRepository.delete(userVerification);
        user.setActive(true);
        userService.saveUser(user);
    }
    private void doGenerateToken(UserDTO map) {

    }

    @Override
    public void createCustomer(CustomerSignUpDTO dto) {
        List<String> errors = new ArrayList<String>();
        if(userService.userIsExistByEmail(dto.getEmail())){
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

            userService.saveUser(savedUser);
            String code = UUID.randomUUID().toString();
            mailService.sendEmail(dto.getEmail(),"Acount verification",
                    "Your verifictaion code is "+ code

                            +"Or you can confirm your account by clicking here : "
                            + "http://localhost:4200/verify-account/"+savedUser.getId()+"/"+code
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
}
