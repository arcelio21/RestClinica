package com.example.dtomapper.speciality;

import com.example.dto.speciality.speciality.SpecialityGetDto;
import com.example.dto.speciality.speciality.SpecialitySaveDto;
import com.example.dto.speciality.speciality.SpecialityUpdateDto;
import com.example.entity.speciality.Tspeciality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoSpecialityMapper {

    DtoSpecialityMapper INSTANCE = Mappers.getMapper(DtoSpecialityMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "save.name",target = "name")
    })
    Tspeciality SpecialitySaveDtoToTspeciality(SpecialitySaveDto save);

    @Mappings({
            @Mapping(source = "update.id", target = "id"),
            @Mapping(source = "update.name",target = "name")
    })
    Tspeciality SpecialityUpdateDtoToTspeciality(SpecialityUpdateDto update);

    @Mappings({
            @Mapping(source = "get.id", target = "id"),
            @Mapping(source = "get.name", target = "name")
    })
    SpecialityGetDto TspecialityToSpecialityGet(Tspeciality get);
}
