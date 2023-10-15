package com.example.dtomapper.address;

import com.example.dto.address.province.ProvinceDto;
import com.example.dto.address.province.ProvinceSaveDto;
import com.example.dto.address.province.ProvinceUpdateDto;
import com.example.entity.address.Tprovince;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoProvinceMapper {

    DtoProvinceMapper INSTANCE = Mappers.getMapper(DtoProvinceMapper.class);

    @Mappings(value = {
            //@Mapping(ignore = true, source = "districts"),
            @Mapping(target = "id",source = "tprovince.id",defaultValue = "0"),
            @Mapping(target ="name",source ="tprovince.name"),

    })
    ProvinceDto tprovinceToProvinceDto(Tprovince tprovince);

    @Mappings(value = {
            @Mapping(target = "id",source = "provinceDto.id", resultType = Integer.class),
            @Mapping(target ="name",source = "provinceDto.name"),
            @Mapping(target = "districts", ignore = true)
    })
    Tprovince provinceDtoToTprovince(ProvinceDto provinceDto);

    @Mappings(value = {
            @Mapping(target = "id",source = "provinceDto.id", resultType = Integer.class),
            @Mapping(target ="name",source = "provinceDto.name"),
            @Mapping(target = "districts", ignore = true)
    })
    Tprovince provinceUpdateDtoToTprovince(ProvinceUpdateDto provinceDto);

    @Mappings(value = {
            @Mapping(target = "id",ignore = true),
            @Mapping(target ="name",source = "provinceDto.name"),
            @Mapping(target = "districts", ignore = true)
    })
    Tprovince provinceSaveDtoToTprovince(ProvinceSaveDto provinceDto);


}
