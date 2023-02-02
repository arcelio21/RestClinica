package com.example.dtomapper.address;

import com.example.dto.address.district.DistrictDto;
import com.example.entity.address.Tdistrict;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DistrictMapper {

    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

    @Mappings({
            @Mapping(source = "tdistrict.id", target = "id"),
            @Mapping(source = "tdistrict.name", target = "name"),
            @Mapping(source = "province.id", target = "provinceId", defaultValue = "0")
    })
    DistrictDto tdistrictToDistrictDto(Tdistrict tdistrict);

    @Mappings(value = {
            @Mapping(target = "id", source = "districtDto.id"),
            @Mapping(target = "name", source = "districtDto.name"),
            @Mapping(target = "villages", ignore = true),
            @Mapping(target = "province.id", source = "districtDto.provinceId", defaultValue = "0"),
            @Mapping(target = "province", ignore = true)
    })
    Tdistrict districtDtoToTprovince(DistrictDto districtDto);
}
