package com.example.dto.address.village;

import com.example.dto.address.district.DistrictDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "VillageDistrict", description = "Tendra la informacion de village y la de su distrito asociado",
        implementation = VillageDistrictDto.class
)
public class VillageDistrictDto {

    @Schema(name = "id", description = "Identificacion unica de village", example = "1", format = "int32", minimum = "1")
    private Integer id;

    @Schema(name = "name", description = "Nombre del village", example = "Las lomas")
    private String name;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(name = "district", description = "Distrito asociado a provincia", implementation = DistrictDto.class)
    private DistrictDto district;
}
