package com.wrfxx.demo10.controller;

import com.wrfxx.demo10.domain.service.UserService;
import com.wrfxx.demo10.domain.service.impl.UserServiceImpl;
import com.wrfxx.demo10.domain.value.dto.CustomerSignUpDTO;
import com.wrfxx.demo10.domain.value.dto.DeveloperSignUpDTO;
import com.wrfxx.demo10.domain.value.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
public class UserController {
    Logger _log = LoggerFactory.getLogger(UserController.class);
    @Qualifier("userService")
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    //@PreAuthorize("hasRole('Role_ADMIN')")
    public ResponseEntity<?> getAllUsers(@RequestParam Map<String, Object> queries){
        return new ResponseEntity<>(userService.getAllUsers(queries), HttpStatus.OK);
    }

    //    @GetMapping("/search")
    //@PreAuthorize("hasRole('Role_ADMIN')")
    /*public Page<UserDTO> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "20") int size,
                                     @RequestParam(defaultValue ="ASC") String order,
                                     @RequestParam(required = false) String firstName,
                                     @RequestParam(required = false) String lastName,
                                     @RequestParam(required = false) String username,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false) String authority ) {
        final Map<String, Object> queries = Map.of(
                "page", page,
                "size", size,
                "order", order,
                "firstName", firstName,
                "lastName", lastName,
                "username", username,
                "email", email,
                "authority", authority
        );
        return userService.getAllUsers(queries);
    }*/

    /*@GetMapping("/search")
    public Page<UserDTO> getAllUsers2(@RequestParam Map<String, Object> queries) {
        return userService.getAllUsers(queries);
    }*/

    @GetMapping("/{id}")
/*    @PreAuthorize("@userService.hasAccess(#id)") // AOP*/
    public ResponseEntity getById(@PathVariable Long id){
        return new ResponseEntity(userService.getUser(id),HttpStatus.OK);
    }



    /*@PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody UpdateUserDTO dto, @PathVariable Long id){
        return new ResponseEntity(userService.updateUser(dto,id),HttpStatus.OK);
    }*/

    /*@DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }*/
}
