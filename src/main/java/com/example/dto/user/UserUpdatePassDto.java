package com.example.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "User Update Password", description = "Se utilza cuando se quiere actualizar contraseña de usuario")
public class UserUpdatePassDto {

    @Schema(name = "indeCard", description = "El valor de la cedula")
    private Long indeCard;

    @Schema(name = "oldPassword", description = "La ultima cotraseña del usuario")
    private String oldPassword;

    @Schema(name = "newPassword", description = "La nueva contraseña del usuario")
    private String newPassword;
}
