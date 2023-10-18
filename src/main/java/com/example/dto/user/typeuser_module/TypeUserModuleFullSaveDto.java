package com.example.dto.user.typeuser_module;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.dto.modules.modulesprivileges.PrivilegeIdDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TypeUserModuleFullSave", description = "Para guardar todo lo relacionado a los modulos, privilegios y tipo de usuario")
public record TypeUserModuleFullSaveDto(

    @NotBlank
    @Schema(name = "nameModule", description = "Path de modulo", example = "api/user")
    String nameModule,

    @NotEmpty
    @NotNull
    @Schema(name = "listPrivilegeIds", description = "Lista de privilegios que se asignaran")
    List<PrivilegeIdDto> listPrivilegeIds,

    @NotNull
    @Min(value = 1)
    @Schema(name = "idStatus", description = "ID de estado", example = "1")
    Integer idStatus,

    @NotNull
    @Min(value = 1)
    @Schema(name = "idTypeUser", description = "ID de tipo de usuario", example = "1")
    Integer idTypeUser
) {
    
}
