package com.example.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "AddressRequest", description = "Dto utilizado para actualizar o guardar registro de direcciones")
public class AddressRequestDto {

    @Schema(name = "id", description = "Identificacion unica de address", example = "1", format = "int32", minimum = "1")
    private Integer id;

    @Schema(name = "villageId", description = "ID de village relacionado a address", example = "4", format = "int32", minimum = "1")
    private Integer villageId;

    @Schema(name = "specificAddress", description = "Direccion mas especifica", example = "San jose, al frente de la iglesia")
    private  String specificAddress;
}
