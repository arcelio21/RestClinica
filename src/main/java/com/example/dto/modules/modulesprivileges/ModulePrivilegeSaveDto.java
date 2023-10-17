package com.example.dto.modules.modulesprivileges;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Schema(name = "Module Privilege Save", description = "Estructura de informacion a guardar Privilegios de modulo")
public class ModulePrivilegeSaveDto {

    @NotNull
    @NotEmpty
    @Schema(name = "privilegeIds", description = "IDs de los privilegios asigando al modulo")
    private List<PrivilegeIdDto> privilegeIds;

    @Schema(name = "moduleId", description = "ID  de modulo al que le sera asignado privilegio", example = "1")
    @NotNull
    @Min(value = 1, message = "ID de modulo no valido")
    private Long moduleId;

    @Schema(name = "statusId", description = "ID de estado del privilegio asignado")
    @Min(value = 1, message = "ID de estado no valido")
    @NotNull
    private Integer statusId;
}
