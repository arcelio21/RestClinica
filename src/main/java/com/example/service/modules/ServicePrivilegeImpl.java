package com.example.service.modules;

import com.example.dto.modules.privileges.PrivilegeDto;
import com.example.dtomapper.modules.DtoPrivilegeMapper;
import com.example.entity.modules.Tprivilege;
import com.example.exception.NoDataFoundException;
import com.example.mapper.modules.MapperPrivilege;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServicePrivilegeImpl implements IServicePrivilege{


	private final MapperPrivilege mapperPrivilege;
	private final DtoPrivilegeMapper dtoPrivilegeMapper;
	@Override
	public List<PrivilegeDto> getAll() {

		Optional<List<Tprivilege>> optionalTprivileges = Optional.ofNullable(this.mapperPrivilege.getAll());

		if(optionalTprivileges.isPresent() && !optionalTprivileges.get().isEmpty()){

			return optionalTprivileges.get()
					.stream()
					.map(this.dtoPrivilegeMapper::TprivilegeToPrivilegDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException("Datos no encontrados");
	}

	@Override
	public PrivilegeDto getById(Integer id) {
		return null;
	}

	@Override
	public Integer update(Tprivilege tprivilege) {
		return null;
	}

	@Override
	public Integer save(Tprivilege tprivilege){
	    return null;
	}
}
