package com.example.dto.address.province;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProvinceSave", description = "Estructura para guardar datos de provincia")
public record ProvinceSaveDto(

    @Schema(description = "Nombre de la provincia", example = "Chiriqui")
    @NotEmpty(message = "Campo no puede estar vacio")
    @Min(value = 4, message = "Valor debe ser de minimo 4 caracteres")
    String name
) {
    
}
