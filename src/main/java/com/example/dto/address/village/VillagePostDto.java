package com.example.dto.address.village;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "VillagePost", description = "DTO para guardar datos de village")
public record VillagePostDto(
    
    @NotBlank
    @Schema(name = "name", description = "Nombre de village", example = "Chiriqui")
    String name, 
    
    @NotNull
    @Min(value = 1)
    @Schema(name = "districtId", description = "ID de distrito asociado a village", example = "1")
    Integer districtId) {
    
}
