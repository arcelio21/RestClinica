package com.example.dto.address.district;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DistrictSaveDto", description = "Dto para guardar distrito")
public record DistrictSaveDto(

    @NotBlank
    @Schema(name = "name", description = "Nombre del distrito", example = "Boquete")
    String name,

    @NotNull
    @Min(value = 1)
    @Schema(name = "provinceId", description = "Identificacion de la provincia a la que pertenece el distrito", example = "1", format = "int32", minimum = "1")
    Integer provinceId
) {
}
