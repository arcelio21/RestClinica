package com.example.service.modules;

import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeUpdateDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegesDto;
import com.example.service.ServiceTemplateCrud;

public interface IServiceModulePrivilege extends ServiceTemplateCrud<ModulePrivilegesDto, Long, ModulePrivilegeUpdateDto,ModulePrivilegeSaveDto>{

}
