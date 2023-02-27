package com.example.dto.user.type_user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Schema(name = "TypeUserGet", description = "Tendra informacion de los tipos de usuario")
public class TypeUserDto {


    @NotNull(message = "ID no puede ser nulo ni vacio")
    @Min(value = 1, message = "El valor no puede ser menor a 1")
    @Schema(name = "id",description = "Identifacion unica de tipo de usuario", example = "1")
    private Integer id;

    @Schema(name = "name", description = "Nombre del tipo de usuario", defaultValue = "Administrador")
    @NotBlank(message = "Atributo no debe contener cadena vacia")
    private String name;
}
