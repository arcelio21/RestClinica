package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TuserDto {

    private  Long idenCard;
    private String name;
    private String lastName;
    private String contact;
    private String email;
    private String fechaNacimiento;
    private String fechaCreacion;

    private Integer idAddress;
}
