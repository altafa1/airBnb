package com.airbnb.controller;

import com.airbnb.payload.JWTToken;
import com.airbnb.payload.LoginDto;
import com.airbnb.payload.UserDto;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authorization")
public class AuthController {
    private AppUserService as;
    private AppUserRepository ar;

    public AuthController(AppUserService as, AppUserRepository ar) {
        this.as = as;
        this.ar = ar;
    }
    @PostMapping("/createuser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
  UserDto savedUser=as.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PostMapping("/createpropertyowner")
    public ResponseEntity<UserDto> createPropertyOwner(@RequestBody UserDto user) {
        UserDto savedUser=as.createPropertyOwner(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PostMapping("/createpropertymanager")
    public ResponseEntity<UserDto> createPropertyManager(@RequestBody UserDto user) {
        UserDto savedUser=as.createPropertyManager(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> userLogin(
            @RequestBody LoginDto dto
    ){
        String token =as.userlogin(dto);
        JWTToken jwtToken=new JWTToken();
        if(token!=null){
            jwtToken.setTokenType("JWT");
            jwtToken.setToken(token);
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }

        return new ResponseEntity<>("invalid username/password",HttpStatus.UNAUTHORIZED);
    }
}
