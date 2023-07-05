package com.example.dto.user.typeuser_module;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "Type User Module Get Data", description = "Estructura de informacion que se devolvera al consultar datos")
public class TypeUserModuleGetDto {

    @Schema(description = "ID  de modulo actual asociado a un privilegio")
    private Long oldIdModulePrivilege;

    @Schema(description = "ID de actual tipo de usuario")
    private Integer oldtTypeUser;

    @Schema(description = "ID de modulo nuevo asociado a un privilegio")
    private Long idModulePrivilege;

    @Schema(description = "ID de tipo de usuario nuevo")
    private Integer typeUser;
}
