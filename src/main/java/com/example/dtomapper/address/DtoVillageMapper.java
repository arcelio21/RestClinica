package com.example.dtomapper.address;

import com.example.dto.address.village.VillageDistrictDto;
import com.example.dto.address.village.VillageDto;
import com.example.dto.address.village.VillagePostDto;
import com.example.dto.address.village.VillageUpdateDto;
import com.example.entity.address.Tvillage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoVillageMapper {

    DtoVillageMapper INSTANCE = Mappers.getMapper(DtoVillageMapper.class);

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
            @Mapping(target = "district.name", source = "tvillage.district.name"),
            @Mapping(target = "district.provinceId", ignore = true)
    })
    VillageDistrictDto tvillageToVillageDistritcDto(Tvillage tvillage);

    @Mappings({
            @Mapping(target = "id", source = "villageDistrictDto.id"),
            @Mapping(target = "name", source = "villageDistrictDto.name"),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "district.id", source = "villageDistrictDto.district.id"),
            @Mapping(target = "district.name", source = "villageDistrictDto.district.name"),
            @Mapping(target = "district.villages", ignore = true),
            @Mapping(target = "district.province", ignore = true)
    })
    Tvillage villageDistrictDtoToTvillage(VillageDistrictDto villageDistrictDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "villagePost.name"),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "district.id", source = "villagePost.districtId")
    })
    Tvillage villagePostToTvillage(VillagePostDto villagePost);

    @Mappings({
            @Mapping(target = "id", source = "villageUpdate.id"),
            @Mapping(target = "name", source = "villageUpdate.name"),
            @Mapping(target = "district", ignore = true),
            @Mapping(target = "district.id", source = "villageUpdate.districtId")
    })
    Tvillage villageUpdateToTvillage(VillageUpdateDto villageUpdate);
}
