package com.example.dto.modules.modulesprivileges;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Schema(name = "Module Privilege Update", description = "Estructura de informacion al actualizar privilegios")
public class ModulePrivilegeUpdateDto {

    @Schema(name = "id", example = "1")
    private Long id;

    @Min(value = 1, message = "ID de privilegio no valido")
    @NotNull
    @Schema(name = "privilegeId", description = "ID de privilegio asigando al modulo", example = "1")
    private Integer privilegeId;

    @Schema(name = "moduleId", description = "ID  de modulo al que le sera asignado privilegio", example = "1")
    @Min(value = 1, message = "Id de modulo no valido")
    @NotNull
    private Long moduleId;

    @Schema(name = "statusId", description = "ID de estado del privilegio asignado")
    @Min(value = 1, message = "ID de estado no valido")
    @NotNull
    private Integer statusId;
}
