package com.example.dto.address.district;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "DistrictDTO", description = "Estructura simple de datos de distritos")
public class DistrictDto {

    @Schema(name = "id", description = "Identificacion unica de Distrito", example = "1", format = "int32", minimum = "1")
    private Integer id;

    @Schema(name = "name", description = "Nombre del distrito", example = "Boquete")
    private String name;

    @JsonInclude(content = JsonInclude.Include.NON_EMPTY)
    @Schema(name = "provinceId", description = "Identificacion de la provincia a la que pertenece el distrito", example = "1", format = "int32", minimum = "1")
    private Integer provinceId;
}
