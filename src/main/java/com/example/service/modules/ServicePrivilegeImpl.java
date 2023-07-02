package com.example.service.modules;

import com.example.dto.modules.privileges.PrivilegeDto;
import com.example.dto.modules.privileges.PrivilegeSaveDto;
import com.example.dto.modules.privileges.PrivilegeUpdateDto;
import com.example.dtomapper.modules.DtoPrivilegeMapper;
import com.example.entity.modules.Tprivilege;
import com.example.exception.NoDataFoundException;
import com.example.exception.modules.privilege.PrivilegeNotSaveException;
import com.example.exception.modules.privilege.PrivilegeNotUpdateException;
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

	/**
	 * Obtiene todos los objetos PrivilegeDto.
	 *
	 * @return Lista de objetos PrivilegeDto
	 * @throws NoDataFoundException si no se encuentran datos
	 */
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

	/**
	 * Obtiene un objeto PrivilegeDto por su ID.
	 *
	 * @param id ID del objeto PrivilegeDto a buscar
	 * @return Objeto PrivilegeDto correspondiente al ID proporcionado
	 * @throws NoDataFoundException si no se encuentra ningún objeto con el ID proporcionado
	 *         o si se proporciona un valor de ID no válido
	 */
	@Override
	public PrivilegeDto getById(Integer id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		return Optional.of(id)
				.map(this.mapperPrivilege::getByid)
				.map(this.dtoPrivilegeMapper::TprivilegeToPrivilegDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

	/**
	 * Actualiza un objeto Privilege a partir de los datos proporcionados en PrivilegeUpdateDto.
	 *
	 * @param privilegeUpdateDto Objeto PrivilegeUpdateDto con los datos de actualización
	 * @return Número de filas afectadas por la actualización
	 * @throws PrivilegeNotUpdateException si los datos proporcionados no son válidos para la actualización
	 *         o si se produce un error durante la actualización
	 */
	@Override
	public Integer update(PrivilegeUpdateDto privilegeUpdateDto) {

		if(privilegeUpdateDto==null || privilegeUpdateDto.getId()==null || privilegeUpdateDto.getId()<=0
			|| privilegeUpdateDto.getName()==null || privilegeUpdateDto.getName().trim().isEmpty()){

			throw new PrivilegeNotUpdateException("Data No Valid", privilegeUpdateDto);
		}

		Optional<Integer> rowAffected= Optional.of(privilegeUpdateDto)
				.map(this.dtoPrivilegeMapper::privilegeUpdateDtoToTprivilege)
				.map(this.mapperPrivilege::update);

		if (rowAffected.isEmpty() || rowAffected.get()<1){
			throw new PrivilegeNotUpdateException("Error de actualizacion, datos no encontrados", privilegeUpdateDto);
		}

		return rowAffected.get();
	}

	@Override
	public Integer save(PrivilegeSaveDto privilegeSaveDto){

		if(privilegeSaveDto==null || privilegeSaveDto.getName()==null || privilegeSaveDto.getName().trim().isEmpty()){
			throw new PrivilegeNotSaveException("Datos no validos", privilegeSaveDto);
		}

		Integer rowAffected = Optional.of(privilegeSaveDto)
				.map(this.dtoPrivilegeMapper::privilegeSaveDtoToTprivilege)
				.map(this.mapperPrivilege::save)
				.orElseThrow(()-> new PrivilegeNotSaveException("Datos no válidos",privilegeSaveDto));

		if(rowAffected==null || rowAffected<=0){
			throw new PrivilegeNotSaveException("Error al guardar informacion", privilegeSaveDto);
		}

	    return rowAffected;
	}
}
