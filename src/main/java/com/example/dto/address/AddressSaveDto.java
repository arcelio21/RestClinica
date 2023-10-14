package com.example.dto.address;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AddressSave", description = "DTO para guardar datos de direccion")
public record AddressSaveDto(

    @NotNull
    @Min(value = 1)
    @Schema(name = "villageId", description = "ID de village asociado a direccion")
    Integer villageId,

    @NotBlank
    @Schema(name = "specificAddress", description="Dirección especifica o extra")
    String specificAddress
) {
    
}
