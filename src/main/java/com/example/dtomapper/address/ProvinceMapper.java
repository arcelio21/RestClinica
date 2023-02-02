package com.example.dtomapper.address;

import com.example.dto.address.province.ProvinceDto;
import com.example.entity.address.Tprovince;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface ProvinceMapper {

    ProvinceMapper INSTANCE = Mappers.getMapper(ProvinceMapper.class);

    @Mappings(value = {
            //@Mapping(ignore = true, source = "districts"),
            @Mapping(target = "ID",source = "tprovince.id",defaultValue = "0"),
            @Mapping(target ="nombre",source ="tprovince.name", defaultValue = ""),

    })
    ProvinceDto tprovinceToProvinceDto(Tprovince tprovince);

    @Mappings(value = {
            //@Mapping(ignore = true, source = "districts"),
            @Mapping(target = "id",source = "provinceDto.ID", resultType = Integer.class),
            @Mapping(target ="name",source = "provinceDto.nombre")
    })
    Tprovince provinceDtoToTprovince(ProvinceDto provinceDto);


}
