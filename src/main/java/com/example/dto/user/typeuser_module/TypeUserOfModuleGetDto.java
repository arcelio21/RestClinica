package com.example.dto.user.typeuser_module;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Datos de tipo de usuario asignado a un modulo
 * @param idTypeUser
 * @param typeUser
 */
@Schema(description = "Tipo de usuario asignado a un modulo")
public record TypeUserOfModuleGetDto(
       @Schema(description = "ID type user")
        Integer idTypeUser,
        @Schema(description = "Name type user")
        String typeUser
) {
}
