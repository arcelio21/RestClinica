package com.example.dto.user.type_user;

import javax.validation.constraints.NotBlank;
import com.example.dto.ValidateGroupA;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TypeUserPostDto", description = "Dto para guardar tipo de usuario")
public record TypeUserPostDto(

    @Schema(name = "name", description = "Nombre del tipo de usuario", defaultValue = "Administrador")
    @NotBlank(message = "Atributo no debe contener cadena vacia", groups = {ValidateGroupA.class})
    String name
) {
    
}
