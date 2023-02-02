package com.example.dto.address.district;

import com.example.dto.address.province.ProvinceDto;
import com.example.entity.address.Tprovince;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "DistrictAll")
public class DistrictAllDto {

    @Schema(name = "id", description = "Identificacion unica de Distrito", example = "1", format = "int32", minimum = "1")
    private Integer id;

    @Schema(name = "name", description = "Nombre del distrito", example = "Boquete")
    private String name;

    @Schema(name = "province", description = "Datos de la provincia asociada al distrito", ref = "#/components/schemas/ProvinceDto")
    private ProvinceDto tprovince;

}
