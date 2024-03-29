package com.example.dto.modules;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "ModulesDto", description = "Para definir informacion de los datos de Modules")
public class ModulesDto {

    @Schema(name = "id", description = "Identificación única de modules")
    @NotNull(message = "'ID' no puede tener un valor nulo")
    @Min(value = 1, message = "'ID' debe ser igual o mayor a 1")
    private  Long id;

    @Schema(name = "name", description = "Path de modulo")
    @NotBlank(message = "'Name' no puede contener una cadena vacia")
    private String name;
}
