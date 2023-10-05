package com.example.dto.user.type_user_reg;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Estructura de datos para relacionar un usuario con tipo de usuario.
 *
 * @param idUserReg   ID de usuario (debe ser mayor o igual a 1).
 * @param idTypeUser  ID de tipo de usuario asignado (debe ser mayor o igual a 1).
 * @param idStatus    ID de estado de registro (debe ser mayor o igual a 1).
 */
@Schema(name = "UserTypeRegSaveDto", description = "Estructua de datos para relacionar un usuario con tipo de usuario")
public record UserTypeRegSaveDto(

    @Min(value=1)
    @NotNull
    @Schema(name = "idUserReg", description="ID de usuario")
    Long idUserReg,

    @Min(value=1)
    @NotNull
    @Schema(name = "idTypeUser", description = "ID de tipo de usuario asignado")
    Integer idTypeUser,

    @Min(value=1)
    @NotNull
    @Schema(name = "idStatus", description="ID de estado de registro")
    Integer idStatus
) {
    
}
