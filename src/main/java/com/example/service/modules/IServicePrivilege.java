package com.example.service.modules;

import com.example.dto.modules.privileges.PrivilegeDto;
import com.example.dto.modules.privileges.PrivilegeUpdateDto;
import com.example.entity.modules.Tprivilege;
import com.example.service.ServiceTemplateCrud;

public interface IServicePrivilege extends ServiceTemplateCrud<PrivilegeDto, Integer, PrivilegeUpdateDto,Tprivilege>{

}
