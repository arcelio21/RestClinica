package com.example.dto.modules.modulesprivileges;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(name = "Module Privilege Get", description = "Se utilizara para definir estructura de informacion cuando se consulta de datos")
@Builder
@Getter
public class ModulePrivilegesDto {

    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "privilegeId", description = "ID de privilegio asigando al modulo", example = "1")
    private Integer privilegeId;
    @Schema(name = "moduleId", description = "ID  de modulo al que le sera asignado privilegio", example = "1")
    private Long moduleId;
    @Schema(name = "statusId", description = "ID de estado del privilegio asignado")
    private Integer statusId;
}
