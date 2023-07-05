package com.example.service.user;

import com.example.dto.user.typeuser_module.TypeUserModuleGetDto;
import com.example.dto.user.typeuser_module.TypeUserModuleSaveDto;
import com.example.dto.user.typeuser_module.TypeUserModuleUpdateDto;
import com.example.entity.user.TtypeUserModule;
import com.example.mapper.user.MapperTypeUserModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceTypeUserModuleImpl implements IServiceTypeUserModule {

	private final MapperTypeUserModule mapperTypeUserModule;


	@Override
	public List<TypeUserModuleGetDto> getAll() {
		return null;
	}

	@Override
	public TypeUserModuleGetDto getById(TypeUserModuleGetDto typeUserModuleGetDto) {
		return null;
	}

	@Override
	public Integer update(TypeUserModuleUpdateDto t) {
		return null;
	}

	@Override
	public Integer save(TypeUserModuleSaveDto t) {
		return null;
	}

	@Override
	public List<TtypeUserModule> getPrivTypeUser(Integer typeUserId) {
		return null;
	}

	@Override
	public List<TtypeUserModule> getModulePriv(Long modulePrivilegeId) {
		return null;
	}
}
