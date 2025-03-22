package com.Auth.AuthService.Service;

import com.Auth.AuthService.entities.UserInfo;
import com.Auth.AuthService.model.UserInfoDto;
import com.Auth.AuthService.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
@Lazy
     private final UserRepository userRepository;

private final PasswordEncoder passwordEncoder;
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo  userInfo =userRepository.findByUsername(username);
        if(userInfo ==null){
            throw new UsernameNotFoundException("could not fount user..............");

        }
        return new CustomUserDetails(userInfo);
    }

    public Boolean signupUser(UserInfoDto userInfoDto){
       userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
       if(Objects.nonNull(checkIfUserAlreadyExits(userInfoDto))){
           return false;
       }
       String userId= UUID.randomUUID().toString();
       userRepository.save(new UserInfo(userId ,userInfoDto.getUsername(),userInfoDto.getPassword(),new HashSet<>()));
       return true;
    }

    private UserInfo checkIfUserAlreadyExits(UserInfoDto userInfoDto) {
       return userRepository.findByUsername(userInfoDto.getUsername())  ;
   }


}
