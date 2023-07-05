package com.example.service.user;
import com.example.dto.user.typeuser_module.TypeUserModuleGetDto;
import com.example.dto.user.typeuser_module.TypeUserModuleSaveDto;
import com.example.dto.user.typeuser_module.TypeUserModuleUpdateDto;
import com.example.service.ServiceTemplateCrud;

import java.util.List;

import com.example.entity.user.TtypeUserModule;

public interface IServiceTypeUserModule extends ServiceTemplateCrud<TypeUserModuleGetDto, TypeUserModuleGetDto, TypeUserModuleUpdateDto, TypeUserModuleSaveDto>{
    List<TtypeUserModule> getPrivTypeUser(Integer typeUserId);
    List<TtypeUserModule> getModulePriv(Long modulePrivilegeId);
}
