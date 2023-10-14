package com.example.dto.address.village;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "VillageUpdate", description = "DTO para datos de actualizacion de village")
public record VillageUpdateDto(

    @NotNull
    @Min(value = 1)
    @Schema(name = "id", description = "ID de registro", example = "1")
    Integer id,

    @NotBlank
    @Schema(name = "name", description = "Nombre de village", example = "Chiriqui")
    String name, 
    
    @NotNull
    @Min(value = 1)
    @Schema(name = "districtId", description = "ID de distrito asociado a village", example = "1")
    Integer districtId
) {
    
}
