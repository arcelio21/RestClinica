package com.example.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "AddressGet", description = "Contendra informacion de las direcciones registradas")
public record AddressGetDto(
        @Schema(name = "id", description = "Identificacion unica de address", example = "1", format = "int32", minimum = "1")
        Integer id,
        @Schema(name = "villageId", description = "ID de village relacionado a address", example = "4", format = "int32", minimum = "1")
        Integer villageId,
        @Schema(name = "villageName", description = "Nombre de village asociado", example = "Las lomas")
        String villageName,
        @Schema(name = "districtName", description = "Nombre de distrito asociado", example = "David")
        String districtName,
        @Schema(name = "provinceName", description = "Nombre de provincia asociada", example = "Chiriquí")
        String provinceName,
        @Schema(name = "specificAddress", description = "Direcion especifica", example = "Al frente de la iglesia")
        String specificAddress) {}
