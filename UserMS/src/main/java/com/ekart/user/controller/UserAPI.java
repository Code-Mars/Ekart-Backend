package com.ekart.user.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.user.dto.HttpMessageDTO;
import com.ekart.user.dto.LoginDTO;
import com.ekart.user.dto.UserDTO;
import com.ekart.user.exception.EkartException;
import com.ekart.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/users")
@Validated
public class UserAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private Environment environment;
    
    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws EkartException {
        Long id=userService.registerUser(userDTO);
        userDTO.setId(id);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);       
    }
    @PostMapping(value="/login")
    public ResponseEntity<UserDTO>loginUser(@RequestBody @Valid LoginDTO loginDTO) throws EkartException{
        return new ResponseEntity<>(userService.loginUser(loginDTO), HttpStatus.ACCEPTED);
    }
    @PostMapping(value="/resetPassword")
    public ResponseEntity<HttpMessageDTO>resetPassword(@RequestBody @Valid LoginDTO loginDTO) throws EkartException{
        userService.resetPassword(loginDTO);
        String msg=environment.getProperty("UserAPI.PASSWORD_RESET_SUCCESSFUL");
        return new ResponseEntity<>(new HttpMessageDTO(msg), HttpStatus.OK);
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) throws EkartException {
        UserDTO userDTO = userService.getUser(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/get/userid")
    public ResponseEntity<Long> getUserId(@RequestBody String email) throws EkartException {
        Long userId = userService.getUserId(email);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }
}
