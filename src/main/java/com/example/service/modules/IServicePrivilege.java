package com.example.service.modules;

import com.example.dto.modules.privileges.PrivilegeDto;
import com.example.dto.modules.privileges.PrivilegeSaveDto;
import com.example.dto.modules.privileges.PrivilegeUpdateDto;
import com.example.service.ServiceTemplateCrud;

public interface IServicePrivilege extends ServiceTemplateCrud<PrivilegeDto, Integer, PrivilegeUpdateDto, PrivilegeSaveDto>{

}
