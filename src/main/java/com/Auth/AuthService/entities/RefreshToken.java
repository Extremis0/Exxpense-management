package com.Auth.AuthService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens")
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    private  String token;

    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "id" ,referencedColumnName = "user_id")
    private  UserInfo userInfo;


}
