package com.example.dto.address.province;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Schema(name = "ProvinceDto",description = "Estructura de respuesta de los datos")
public class  ProvinceDto{

    @Schema(description = "ID de la province",format = "int32", minimum = "1", example = "1")
    Integer ID;
    @Schema(description = "Nombre de la provincia", example = "Chiriqui")
    String nombre;
}
