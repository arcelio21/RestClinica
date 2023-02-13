package com.example.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "User Update Password", description = "Se utilza cuando se quiere actualizar contraseña de usuario")
public class UserUpdatePassDto {

    @Schema(name = "IdentificationCard", description = "El valor de la cedula")
    private Long indeCard;

    @Schema(name = "Old Paswword", description = "La ultima cotraseña del usuario")
    private String oldPassword;

    @Schema(name = "New Password", description = "La nueva contraseña del usuario")
    private String newPassword;
}
