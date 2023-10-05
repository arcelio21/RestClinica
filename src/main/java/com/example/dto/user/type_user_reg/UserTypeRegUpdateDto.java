package com.example.dto.user.type_user_reg;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para actualizar un registro de usuario tipo-registro.
 *
 * @param id        ID de userTypeReg.
 * @param idUserReg ID de usuario.
 * @param idTypeUser ID de tipo de usuario asignado.
 * @param idStatus  ID de estado de registro.
 */
@Schema(name = "UserTypeRegUpdateDto", description = "Estructura de datos para actualizar informacion de usuario con tipo de usuario asignado")
public record UserTypeRegUpdateDto(
    
    @Min(value=1)
    @NotNull
    @Schema(name = "id", description = "ID de userTypeReg", defaultValue = "1")
    Long id,

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
