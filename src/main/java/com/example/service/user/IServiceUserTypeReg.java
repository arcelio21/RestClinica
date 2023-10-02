package com.example.service.user;

import java.util.List;

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

	List<GET> getByIdUserReg(ID id);

	List<GET> getByIdTypeUser(ID id);

	List<GET> getByIdStatus(ID id);

}
