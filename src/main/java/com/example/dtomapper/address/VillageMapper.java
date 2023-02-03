package com.example.dtomapper.address;

import com.example.dto.address.village.VillageDto;
import com.example.entity.address.Tvillage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VillageMapper {

    VillageMapper INSTACE = Mappers.getMapper(VillageMapper.class);

    @Mappings({
            @Mapping(target = "id",source = "tvillage.id", defaultValue = "0"),
            @Mapping(target = "name", source = "tvillage.name"),
            @Mapping(target = "districtId", source = "tvillage.district.id")
    })
    VillageDto tvillageToVillageDto(Tvillage tvillage);

    @Mappings({
            @Mapping(target = "id", source = "villageDto.id"),
            @Mapping(target = "name", source = "villageDto.name"),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "district.id", source = "villageDto.districtId")
    })
    Tvillage villageDtoToTvillage(VillageDto villageDto);
}
