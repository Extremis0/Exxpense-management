package com.Auth.AuthService.Service;

import com.Auth.AuthService.entities.UserInfo;
import com.Auth.AuthService.entities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomUserDetails extends UserInfo implements UserDetails {

    private  String username;
    private String password;

    public CustomUserDetails(UserInfo byUsername) {

        this.username = byUsername.getUsername();
        this.password = byUsername.getPassword();
        List<GrantedAuthority> auths=new ArrayList<>();
        for(UserRole role: byUsername.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities=auths;
    }

    Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getUsername() {
        return "username";
    }
    public String getPassword() {
        return "password";
    }
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
