package com.example.dto.user.typeuser_module;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Privilegios de modulos asociadio a un tipos de usuario
 * @param idPrivilege
 * @param privilege
 * @param idStatus
 * @param status
 */
@Schema(description = "Privilegios de modulos asociado a un tipo de usuario")
public record PrivilegeOfModuleGetDto(
        @Schema(description = "ID privilege")
        Integer idPrivilege,
        @Schema(description = "Name Privilege")
        String privilege,
        @Schema(description = "ID status")
        Integer idStatus,
        @Schema(description = "Name status")
        String status
) {
}
