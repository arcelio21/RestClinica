package com.example.service.user;

import java.util.List;

import com.example.dto.user.type_user_reg.TypeUserOfUserRegGetDto;
import com.example.dto.user.type_user_reg.UserRegOfTypeUserGetDto;
import com.example.service.ServiceTemplateCrud;

/**
 * Interfaz genérica para definir un servicio relacionado con la entidad de
 * usuario tipo-registro.
 *
 * @param <GET>    El tipo de objeto que se utiliza para obtener datos de
 *                 usuario tipo-registro.
 * @param <ID>     El tipo de dato que se utiliza para identificar registros de
 *                 usuario tipo-registro.
 * @param <UPDATE> El tipo de objeto que se utiliza para actualizar registros de
 *                 usuario tipo-registro.
 * @param <SAVE>   El tipo de objeto que se utiliza para guardar nuevos
 *                 registros de usuario tipo-registro.
 */
public interface IServiceUserTypeReg<GET, ID, UPDATE, SAVE> extends ServiceTemplateCrud<GET, ID, UPDATE, SAVE> {

	List<TypeUserOfUserRegGetDto> getByIdUserReg(ID id);
	
	List<TypeUserOfUserRegGetDto> getByIdUserRegActivated(ID id);

	List<UserRegOfTypeUserGetDto> getByIdTypeUser(Integer id);

	List<GET> getByIdStatus(Integer id);

}
