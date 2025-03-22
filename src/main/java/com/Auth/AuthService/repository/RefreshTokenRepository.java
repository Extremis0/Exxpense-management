package com.Auth.AuthService.repository;

import com.Auth.AuthService.entities.RefreshToken;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken , Integer> {

    Optional<RefreshToken> findByToken(String token);
}
