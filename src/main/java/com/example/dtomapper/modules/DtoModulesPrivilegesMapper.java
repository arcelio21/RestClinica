package com.example.dtomapper.modules;

import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeUpdateDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegesDto;
import com.example.entity.modules.TmodulePrivilege;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoModulesPrivilegesMapper {

    DtoModulesPrivilegesMapper INSTANCE = Mappers.getMapper(DtoModulesPrivilegesMapper.class);

    @Mappings({
            @Mapping(source = "modulePrivilege.id", target = "id"),
            @Mapping(source = "modulePrivilege.privilege.id", target = "privilegeId"),
            @Mapping(source = "modulePrivilege.module.id", target = "moduleId"),
            @Mapping(source = "modulePrivilege.status.id", target = "statusId")
    })
    ModulePrivilegesDto TmodulePrivilegeToModulePrivilegeDto(TmodulePrivilege modulePrivilege);

    @Mappings({
            @Mapping(source = "modulePrivilegeSaveDto.moduleId", target = "module.id"),
            @Mapping(ignore = true, target = "privilege.id"),
            @Mapping(source = "modulePrivilegeSaveDto.statusId", target = "status.id"),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "privilege", ignore = true),
            @Mapping(target = "module", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    TmodulePrivilege ModulePrivilegeSaveDtoToTmodulePrivilege(ModulePrivilegeSaveDto modulePrivilegeSaveDto);

    @Mappings({
            @Mapping(source = "modulePrivilegeUpdateDto.id", target = "id"),
            @Mapping(source = "modulePrivilegeUpdateDto.privilegeId", target = "privilege.id"),
            @Mapping(source = "modulePrivilegeUpdateDto.moduleId", target = "module.id"),
            @Mapping(source = "modulePrivilegeUpdateDto.statusId", target = "status.id"),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "module", ignore = true),
            @Mapping(target = "privilege", ignore = true)
    })
    TmodulePrivilege ModulePrivilegeUpdateDtoToTmoduloPrivilege(ModulePrivilegeUpdateDto modulePrivilegeUpdateDto);
}
