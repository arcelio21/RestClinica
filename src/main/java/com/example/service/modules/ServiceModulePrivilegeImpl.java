package com.example.service.modules;

import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeUpdateDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegesDto;
import com.example.mapper.modules.MapperModulePrivilege;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceModulePrivilegeImpl implements IServiceModulePrivilege{
	
	private final MapperModulePrivilege mapperModulePrivilege;


	/**
	 * @return 
	 */
	@Override
	public List<ModulePrivilegesDto> getAll() {
		return null;
	}

	@Override
	public ModulePrivilegesDto getById(Long id) {
		return null;
	}

	/**
	 * @param t 
	 * @return
	 */
	@Override
	public Integer update(ModulePrivilegeUpdateDto modulePrivilegeUpdateDto) {
		return null;
	}

	/**
	 * @param t 
	 * @return
	 */
	@Override
	public Integer save(ModulePrivilegeSaveDto modulePrivilegeSaveDto) {
		return null;
	}
}
