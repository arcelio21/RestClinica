package com.example.dtomapper.modules;

import com.example.dto.modules.privileges.PrivilegeDto;
import com.example.dto.modules.privileges.PrivilegeSaveDto;
import com.example.dto.modules.privileges.PrivilegeUpdateDto;
import com.example.entity.modules.Tprivilege;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoPrivilegeMapper {

    DtoPrivilegeMapper INSTANCE = Mappers.getMapper(DtoPrivilegeMapper.class);

    @Mappings({
            @Mapping(source = "tprivilege.id", target = "id"),
            @Mapping(source = "tprivilege.namePrivilege", target = "name")
    })
    PrivilegeDto TprivilegeToPrivilegDto(Tprivilege tprivilege);

    @Mappings({
            @Mapping(source = "privilege.name", target = "namePrivilege"),
            @Mapping(target = "id", ignore = true)
    })
    Tprivilege privilegeSaveDtoToTprivilege(PrivilegeSaveDto privilege);

    @Mappings({
            @Mapping(source = "privilege.id", target = "id"),
            @Mapping(source = "privilege.name", target = "namePrivilege")
    })
    Tprivilege privilegeUpdateDtoToTprivilege(PrivilegeUpdateDto privilege);
}
