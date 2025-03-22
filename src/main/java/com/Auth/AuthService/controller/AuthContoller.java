package com.Auth.AuthService.controller;

import com.Auth.AuthService.Service.JwtService;
import com.Auth.AuthService.Service.RefreshTokenService;
import com.Auth.AuthService.Service.UserDetailsServiceImpl;
import com.Auth.AuthService.entities.RefreshToken;
import com.Auth.AuthService.model.UserInfoDto;
import com.Auth.AuthService.response.JwtResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthContoller {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("auth/v1/signup")
     public ResponseEntity signup(@RequestBody UserInfoDto userInfoDto){
         try {
             Boolean isSignUped=userDetailsService.signupUser(userInfoDto);
             if(Boolean.FALSE.equals(isSignUped)){
                 return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
             }
             RefreshToken refreshToken=refreshTokenService.createRefreshToken(userInfoDto.getUsername());
             String jwtToken=jwtService.generateToken(userInfoDto.getUsername());
             return new ResponseEntity<>(JwtResponseDto.builder().accessToken(jwtToken).token(refreshToken.getToken()).build(),HttpStatus.OK);
         }
         catch (Exception ex){
             return new ResponseEntity<>("Exception in user Service" ,HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }
}
