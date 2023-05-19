package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {

    private Long idenCard;
    private String password;
    private String name;
    private String lastName;
    private String contact;
    private String email;
    private LocalDate birthday;
    private String direcSpecific;
    private Integer idVillage;
}
