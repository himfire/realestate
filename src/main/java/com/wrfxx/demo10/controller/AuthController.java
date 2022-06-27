package com.wrfxx.demo10.controller;


import com.wrfxx.demo10.domain.service.impl.AuthServiceImplementation;
import com.wrfxx.demo10.domain.value.dto.CustomerSignUpDTO;
import com.wrfxx.demo10.domain.value.dto.LoginDTO;
import com.wrfxx.demo10.domain.value.dto.Token;
import com.wrfxx.demo10.domain.value.dto.TokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {
    private final AuthServiceImplementation authService;

    public AuthController(@Lazy AuthServiceImplementation authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody CustomerSignUpDTO dto){
        authService.createCustomer(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO dto){
        System.out.println("In login");
        return new ResponseEntity<TokenDTO>(authService.login(dto),HttpStatus.OK);
    }

    @PostMapping("/verify/{id}/{code}")
    public ResponseEntity login(@PathVariable Long id,@PathVariable String code){
        log.info("Id: "+id);
        log.info("Code: "+code);
        authService.userVerify(id,code);
        return new ResponseEntity(HttpStatus.OK);
    }
/*    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> userRegistration (@RequestBody CustomerSignUpDTO request) {
        User user = authService.userRegistration(request);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }*/

    /*@PostMapping("/login")
    public Map<String, Object> login (@RequestBody LoginDTO request) {
        String jwtToken = authService.getJwtTokenAfterUserLogin(request);
        return Collections.singletonMap("token", jwtToken);
    }*/
}
