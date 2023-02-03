package com.example.dto.address.village;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "Village", description = "Datos principales de Village", implementation = VillageDto.class)
public class VillageDto {

    @Schema(name = "id", description = "Identificacion unica de village", example = "1", format = "int32", minimum = "1")
    private Integer id;

    @Schema(name = "name", description = "Nombre del village", example = "Las lomas")
    private String name;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(name = "districtId", description = "Id de distrito asociado a un village", example = "1", format = "int32", minimum = "1")
    private Integer districtId;
}
