package com.example.dto.user.typeuser_module;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Datos de modulos asociado a un tipo de usuario
 * @param idModule
 * @param nameModule
 */
@Schema(description = "Modulos de un tipo de usuario")
public record ModuleOfTypeUserGetDto(
        @Schema(description = "ID module")
        Long idModule,
        @Schema(description = "Name module")
        String nameModule
) {
}
