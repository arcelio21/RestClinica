package com.example.service.modules;

import java.util.List;

import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeUpdateDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegesDto;
import com.example.dto.modules.modulesprivileges.PrivilegeModuleDetailGetDto;
import com.example.service.ServiceTemplateCrud;

public interface IServiceModulePrivilege extends ServiceTemplateCrud<ModulePrivilegesDto, Long, ModulePrivilegeUpdateDto,ModulePrivilegeSaveDto>{

    PrivilegeModuleDetailGetDto getPrivilegeModuleDetailsById(Long id);
    List<PrivilegeModuleDetailGetDto> getPrivilegeModuleDetails();
    List<PrivilegeModuleDetailGetDto> getPrivilegeModuleDetailsByModuleId(Long idModule);
}
