package com.example.dto.address.province;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProvinceUpdateDto", description = "Actualizar datos de provincia")
public record ProvinceUpdateDto(

    @Schema(name = "id",description = "ID de la province",format = "int32", minimum = "1", example = "1")
    @Min(value = 1)
    Integer id,

    @Schema(description = "Nombre de la provincia", example = "Chiriqui")
    @NotEmpty(message = "Campo no puede estar vacio")
    @Min(value = 4, message = "Valor debe ser de minimo 4 caracteres")
    String name
) {
    
}
