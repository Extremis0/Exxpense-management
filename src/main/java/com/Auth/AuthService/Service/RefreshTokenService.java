package com.Auth.AuthService.Service;

import com.Auth.AuthService.entities.RefreshToken;
import com.Auth.AuthService.entities.UserInfo;
import com.Auth.AuthService.repository.RefreshTokenRepository;
import com.Auth.AuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
  @Autowired
  RefreshTokenRepository refreshTokenRepository;
   @Autowired
  UserRepository userRepository;


  public RefreshToken createRefreshToken(String username) {
    UserInfo userInfoExtracted =userRepository.findByUsername(username);

     RefreshToken refreshToken= RefreshToken.builder()
            .userInfo(userInfoExtracted)
            .token(UUID.randomUUID().toString())
            .expiryDate(Instant.now().plusMillis(600000)) // 10 minutes
            .build();
     return refreshTokenRepository.save(refreshToken);
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      // Simulating token deletion
      throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new request.");
    }
    return token;
  }
    public Optional<RefreshToken> findByToken(String token){
    return refreshTokenRepository.findByToken(token);
    }
}
