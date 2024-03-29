package com.example.dto.address.province;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Schema(name = "ProvinceDto",description = "Estructura de respuesta de los datos")
public class  ProvinceDto{

    @Schema(name = "id",description = "ID de la province",format = "int32", minimum = "1", example = "1")
    private  Integer id;
    @Schema(description = "Nombre de la provincia", example = "Chiriqui")
    @NotEmpty(message = "Campo no puede estar vacio")
    @Min(value = 4, message = "Valor debe ser de minimo 4 caracteres")
    private String name;
}
