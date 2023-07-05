package com.example.dto.user.typeuser_module;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Schema(name = "Type User Module Update Data", description = "Estructura de informacion para guardar datos")
public class TypeUserModuleUpdateDto {

    @NotNull
    @Min(1)
    @Schema(description = "ID  de modulo asociado a un privilegio")
    private Long idModulePrivilege;

    @NotNull
    @Min(1)
    @Schema(description = "ID de tipo de usuario")
    private Integer typeUser;
}
