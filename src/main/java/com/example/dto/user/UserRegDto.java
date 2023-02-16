package com.example.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@Schema(name = "UserReg", description = "Tendra toda la informacion disponible de los usuarios registrados")
public class UserRegDto {

    private Integer id;
    private Long idenCard;
    private String name;
    private String lastName;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) //Para que solo se muestra cuando se va obtener un datos y no ingresarlos
    private String password;
    private String contact;
    private String email;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaCreacion;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer addressId;




}
