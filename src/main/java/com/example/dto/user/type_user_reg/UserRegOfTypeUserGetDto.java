package com.example.dto.user.type_user_reg;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Estructura de datos para usuarios que pertenecen a un tipo de usuario.
 *
 * @param id       ID de registro.
 * @param fullName     Nombre completo de usuario.
 * @param idenCard Identificación de usuario.
 * @param status   Estado de registro.
 */
@Schema(name = "UserRegOfTypeUserGetDto", description = "Estructura de datos para usuario que pertenecen a un tipo de usuario")
public record UserRegOfTypeUserGetDto(
    
    @Min(value = 1)
    @Schema(name = "id", description = "ID de registro", example = "1")
    Long id,
    
    @NotBlank
    @Schema(name = "fullName", description = "Nombre completo de usuario", example = "Arcelio")
    String fullName,

    @NotNull
    @Length(min = 12)
    @Schema(name = "idenCard", description = "Identificacion de usuario", example = "12000704001435")
    Long idenCard,

    @NotBlank
    @Schema(name = "status", description = "Estado de registro", example = "Activated")
    String status
) {
    
}
