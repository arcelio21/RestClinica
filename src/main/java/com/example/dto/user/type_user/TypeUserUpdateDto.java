package com.example.dto.user.type_user;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.dto.ValidateGroupA;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TypeUserUpdateDto", description = "Dto para actualizar tipo de usuario")
public record TypeUserUpdateDto(

    @NotNull(message = "ID no puede ser nulo ni vacio")
    @Min(value = 1, message = "El valor no puede ser menor a 1")
    @Schema(name = "id",description = "Identifacion unica de tipo de usuario", example = "1")
    Integer id,

    @Schema(name = "name", description = "Nombre del tipo de usuario", defaultValue = "Administrador")
    @NotBlank(message = "Atributo no debe contener cadena vacia", groups = {ValidateGroupA.class})
    String name
) {
    
}
