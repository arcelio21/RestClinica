package com.example.dto.user.user_reg;

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

    private Long id;
    private Long idenCard;
    private String name;
    private String lastName;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String password;
    private String contact;
    private String email;
    private LocalDate birthday;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime creationDate;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long addressId;




}
