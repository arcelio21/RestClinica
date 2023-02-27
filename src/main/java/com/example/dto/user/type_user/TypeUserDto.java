package com.example.dto.user.type_user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "TypeUserGet", description = "Tendra informacion de los tipos de usuario")
public class TypeUserDto {

    @Schema(name = "id",description = "Identifacion unica de tipo de usuario", example = "1")
    private Integer id;

    @Schema(name = "name", description = "Nombre del tipo de usuario", defaultValue = "Administrador")
    private String name;
}
