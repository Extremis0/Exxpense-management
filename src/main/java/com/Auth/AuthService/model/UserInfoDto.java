package com.Auth.AuthService.model;

import com.Auth.AuthService.entities.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.class)
public class UserInfoDto extends UserInfo {

    private String userName;

    private String lastName;

    private Long phoneNumber;

    private String email;

}
