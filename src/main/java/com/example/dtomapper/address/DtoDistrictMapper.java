package com.example.dtomapper.address;

import com.example.dto.address.district.DistrictAllDto;
import com.example.dto.address.district.DistrictDto;
import com.example.dto.address.district.DistrictSaveDto;
import com.example.dto.address.district.DistrictUpdateDto;
import com.example.entity.address.Tdistrict;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoDistrictMapper {

    DtoDistrictMapper INSTANCE = Mappers.getMapper(DtoDistrictMapper.class);

    @Mappings({
            @Mapping(source = "tdistrict.id", target = "id"),
            @Mapping(source = "tdistrict.name", target = "name"),
            @Mapping(source = "province.id", target = "provinceId")
    })
    DistrictDto tdistrictToDistrictDto(Tdistrict tdistrict);

    @Mappings(value = {
            @Mapping(target = "id", source = "districtDto.id"),
            @Mapping(target = "name", source = "districtDto.name"),
            @Mapping(target = "villages", ignore = true),
            @Mapping(target = "province.id", source = "districtDto.provinceId", defaultValue = "0"),
            @Mapping(target = "province", ignore = true)
    })
    Tdistrict districtDtoToTdistrict(DistrictDto districtDto);

    @Mappings({
            @Mapping(source = "tdistrict.id", target = "id"),
            @Mapping(source = "tdistrict.name", target = "name"),
            @Mapping(source = "tdistrict.province.id", target = "tprovince.id"),
            @Mapping(source = "tdistrict.province.name", target = "tprovince.name"),
            @Mapping(target = "tprovince", ignore = true)
    })
    DistrictAllDto tdistrictToDistrictAll(Tdistrict tdistrict);

    @Mappings(value = {
            @Mapping(target = "id", source = "districtAllDto.id"),
            @Mapping(target = "name", source = "districtAllDto.name"),
            @Mapping(target = "villages", ignore = true),
            @Mapping(target = "province.id", source = "districtAllDto.tprovince.id"),
            @Mapping(target = "province.name", source = "districtAllDto.tprovince.name"),
            @Mapping(target = "province", ignore = true),
            @Mapping(target = "province.districts", ignore = true)
    })
    Tdistrict districtALlToTdistrict(DistrictAllDto districtAllDto);

    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "districtDto.name"),
            @Mapping(target = "villages", ignore = true),
            @Mapping(target = "province.id", source = "districtDto.provinceId", defaultValue = "0"),
            @Mapping(target = "province", ignore = true)
    })
    Tdistrict districtSaveDtoToTdistrict(DistrictSaveDto districtDto);

    @Mappings(value = {
            @Mapping(target = "id", source = "districtDto.id"),
            @Mapping(target = "name", source = "districtDto.name"),
            @Mapping(target = "villages", ignore = true),
            @Mapping(target = "province.id", source = "districtDto.provinceId", defaultValue = "0"),
            @Mapping(target = "province", ignore = true)
    })
    Tdistrict districtUpdateDtoToTdistrict(DistrictUpdateDto districtDto);
}
