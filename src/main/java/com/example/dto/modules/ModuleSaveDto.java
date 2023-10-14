package com.example.dto.modules;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ModulesSaveDto", description = "Para definir informacion de los datos de Modules para actualizar")
public record ModuleSaveDto(

    @Schema(name = "name", description = "Path de modulo")
    @NotBlank(message = "'Name' no puede contener una cadena vacia")
    String name
) {
    
}
