package com.example.dto.modules.modulesprivileges;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PrivilegeModuleDetailGet", description = "DTO para ver informacion del privilegio que se le asigna a un modulo")
public record PrivilegeModuleDetailGetDto(
    
    @NotNull
    @Min(1)
    @Schema(name = "id", description = "ID de privilegio de modulo", defaultValue = "1")
    Long id,

    @NotBlank
    @Schema(name = "namePrivilege", description = "Nombre del privilegio asignado a modulo", defaultValue = "GET")
    String namePrivilege,

    @NotBlank
    @Schema(name = "nameModule", description = "Nombre de modulo", defaultValue = "/api/user")
    String nameModule,

    @NotBlank
    @Schema(name = "nameStatus", description = "Nombre de estado", defaultValue = "Activated")
    String nameStatus
) {
    
}
