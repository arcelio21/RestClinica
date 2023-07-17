package com.example.dto.user.typeuser_module;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Estructura de informacion de modulos asociados a un tipo de usuario
 * @param idModule
 * @param nameModule
 * @param idTypeUser
 * @param typeUser
 */
@Schema(description = "Estructura de informacion de los modulos que tienes asociado un tipo de usuarios ")
public record ModuleTypeUserGet(
        @Schema(description = "ID module")
        Long idModule,
        @Schema(description = "Name module")
        String nameModule,
        @Schema(description = "ID type user")
        Integer idTypeUser,
        @Schema(description = "Name type user")
        String typeUser

) {
}
