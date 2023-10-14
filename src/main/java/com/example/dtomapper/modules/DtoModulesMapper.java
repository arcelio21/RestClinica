package com.example.dtomapper.modules;

import com.example.dto.modules.ModuleSaveDto;
import com.example.dto.modules.ModulesDto;
import com.example.dto.modules.ModulesUpdateDto;
import com.example.entity.modules.Tmodule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoModulesMapper {

    DtoModulesMapper INSTANCE = Mappers.getMapper(DtoModulesMapper.class);

    @Mappings({
            @Mapping(source = "module.id", target = "id"),
            @Mapping(source = "module.nameModule", target = "name")
    })
    ModulesDto TmoduleToModulesDto(Tmodule module);


    @Mappings({
            @Mapping(source = "module.id", target = "id"),
            @Mapping(source = "module.name", target = "nameModule")
    })
    Tmodule modulesUpdateToTmodule(ModulesUpdateDto module);

    @Mappings({
            @Mapping(target ="id", ignore = true),
            @Mapping(source = "module.name", target = "nameModule")
    })
    Tmodule modulesSaveToTmodule(ModuleSaveDto module);

}
