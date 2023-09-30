package com.example.service.user;

import com.example.dto.user.typeuser_module.*;
import com.example.service.ServiceTemplateCrud;

import java.util.List;

public interface IServiceTypeUserModule<GET,ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET,ID,UPDATE,SAVE>{

    List<ModuleTypeUserGetDto> getModuleAndTypeUserDistinct();
    List<ModuleOfTypeUserGetDto> getModuleDistinctByIdTypeUserAndStatusActived(Integer idTypeUser);
    List<ModuleOfTypeUserGetDto> getModuleDistinctByIdTypeUserAndIdStatus(Integer idTypeUser, Integer idStatus);
    List<TypeUserOfModuleGetDto> getTypeUserDistinctByIdModuleAndStatusActivated(Long idModule);
    List<TypeUserOfModuleGetDto> getTypeUserDistinctByIdModuleAndIdStatus(Long idModule, Integer idStatus);
    List<PrivilegeOfModuleGetDto> getPrivelegeOfModuleByIdTypeUserAndIdModuleAndStatusActived(Integer idTypeUser, Long idModule);
    List<TypeUserModuleGetDto> getTypeModulePrivilegeByidTypeUserAndStatusActived(Integer idTypeUser);

    List<ModuleRoute> getRouteModule();
}
