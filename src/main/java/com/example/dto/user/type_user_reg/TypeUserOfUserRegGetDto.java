package com.example.dto.user.type_user_reg;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa datos del tipo de usuario asociado a un usuario registrado.
 *
 * @param id      ID de registro.
 * @param typeUser Nombre de tipo de usuario.
 * @param status  Nombre de estado.
 */
@Schema(name = "TypeUserOfUserRegGet", description = "Datos tipo de usuario asociado a un usuario")
public record TypeUserOfUserRegGetDto(
    
    @Min(value = 1)
    @Schema(name = "id", description = "ID de registro", example = "1")
    Long id,
    
    @NotBlank
    @Schema(name = "typeUser", description = "Nombre de tipo de usuario", example = "Pacient")
    String typeUser, 

    @NotBlank
    @Schema(name = "status", description = "Nombre de estado", example = "Activated")
    String status
    ){
    
}
