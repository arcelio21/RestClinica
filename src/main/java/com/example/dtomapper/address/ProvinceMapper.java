package com.example.dtomapper.address;

import com.example.dto.address.province.ProvinceDto;
import com.example.entity.address.Tprovince;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProvinceMapper {

    ProvinceMapper INSTANCE = Mappers.getMapper(ProvinceMapper.class);

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


}
