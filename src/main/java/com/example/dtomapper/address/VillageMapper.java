package com.example.dtomapper.address;

import com.example.dto.address.village.VillageDistrictDto;
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

    @Mappings({
            @Mapping(target = "id", source = "tvillage.id"),
            @Mapping(target = "name",source = "tvillage.name"),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "district.id", source = "tvillage.district.id"),
            @Mapping(target = "district.name", source = "tvillage.district.name")
    })
    VillageDistrictDto tvillageToVillageDistritcDto(Tvillage tvillage);

    @Mappings({
            @Mapping(target = "id", source = "villageDistrictDto.id"),
            @Mapping(target = "name", source = "villageDistrictDto.name"),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "district.id", source = "villageDistrictDto.district.id"),
            @Mapping(target = "district.name", source = "villageDistrictDto.district.name")
    })
    Tvillage villageDistrictDtoToTvillage(VillageDistrictDto villageDistrictDto);
}
