package com.example.dtomapper.user;

import com.example.dto.user.typeuser_module.*;
import com.example.entity.user.TtypeUserModule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoTypeUserModuleMapper {

    DtoTypeUserModuleMapper INSTANCE = Mappers.getMapper(DtoTypeUserModuleMapper.class);

    @Mappings({
            @Mapping(source = "userModule.id",target = "id"),
            @Mapping(source = "userModule.typeUser.nameTypeUser", target = "nameTypeUser"),
            @Mapping(source = "userModule.modulePrivilegeId.module.nameModule", target = "nameModule"),
            @Mapping(source = "userModule.modulePrivilegeId.privilege.namePrivilege", target = "namePrivilege"),
            @Mapping(source = "userModule.modulePrivilegeId.status.name", target = "nameStatus")
    })
    TypeUserModuleGetDto tTypeUserModuleToTypeUserModuleGetDto(TtypeUserModule userModule);

    @Mappings({
            @Mapping(source = "userModule.typeUser.id", target = "idTypeUser"),
            @Mapping(source = "userModule.typeUser.nameTypeUser", target = "typeUser")
    })
    TypeUserOfModuleGetDto tTypeUserModuleToTypeUserOfModuleGetDto(TtypeUserModule userModule);

    @Mappings({
            @Mapping(source = "userModule.modulePrivilegeId.module.id",target = "idModule"),
            @Mapping(source = "userModule.modulePrivilegeId.module.nameModule", target = "nameModule")
    })
    ModuleOfTypeUserGetDto tTypeUserModuleToModuleOfTypeUserGetDto(TtypeUserModule userModule);

    @Mappings({
            @Mapping(source = "userModule.modulePrivilegeId.module.id", target = "idModule"),
            @Mapping(source = "userModule.modulePrivilegeId.module.nameModule", target = "nameModule"),
            @Mapping(source = "userModule.typeUser.id",target = "idTypeUser"),
            @Mapping(source = "userModule.typeUser.nameTypeUser", target = "typeUser")
    })
    ModuleTypeUserGetDto tTypeUserModuleToModuleTypeUserGetDto(TtypeUserModule userModule);

    @Mappings({
            @Mapping(source = "userModule.modulePrivilegeId.privilege.id", target = "idPrivilege"),
            @Mapping(source = "userModule.modulePrivilegeId.privilege.namePrivilege", target = "privilege"),
            @Mapping(source = "userModule.modulePrivilegeId.status.id", target = "idStatus"),
            @Mapping(source = "userModule.modulePrivilegeId.status.name", target = "status")
    })
    PrivilegeOfModuleGetDto tTypeUserModuleToPrivilegeOfModuleGetDto(TtypeUserModule userModule);

    @Mappings({
            @Mapping(source = "save.typeUser", target = "typeUser.id"),
            @Mapping(source = "save.idModulePrivilege", target = "modulePrivilegeId.id"),
            @Mapping(target = "modulePrivilegeId", ignore = true),
            @Mapping(target = "typeUser", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    TtypeUserModule TypeUserModuleSaveDtoToTtypeUserModule(TypeUserModuleSaveDto save);

    @Mappings({
            @Mapping(source = "update.id", target = "id"),
            @Mapping(source = "update.idModulePrivilege", target = "modulePrivilegeId.id"),
            @Mapping(source = "update.idTypeUser", target = "typeUser.id"),
            @Mapping(target = "modulePrivilegeId", ignore = true),
            @Mapping(target = "typeUser", ignore = true)
    })
    TtypeUserModule TypeUserModuleUpdateDtoToTtypeUserModule(TypeUserModuleUpdateDto update);

}
