package com.example.dto.user.typeuser_module;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "Type User Module Get Data", description = "Estructura de informacion que se devolvera al consultar datos")
public class TypeUserModuleGetDto {

    @Schema(description = "ID tipo de usuario asignado a un privilegio de un modulo")
    private Long id;

    @Schema(description = "Nombre de modulo")
    private String nameModule;

    @Schema(description = "Nombre de tipo de usuario")
    private String nameTypeUser;

    @Schema(description = "Nombre de privilegio")
    private String namePrivilege;

}
