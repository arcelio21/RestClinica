package com.example.service;

import java.util.List;

/**
 * Interfaz genérica que define las operaciones CRUD (crear, leer, actualizar, eliminar) para un tipo de entidad determinado.
 * @param <GET> El tipo de objeto utilizado para obtener una entidad por su ID Y PARA LISTAR DATOS.
 * @param <ID> El tipo de dato utilizado para identificar una entidad.
 * @param <UPDATE> El tipo de objeto utilizado para actualizar una entidad.
 * @param <SAVE> El tipo de objeto utilizado para guardar una nueva entidad.
 */
public interface ServiceTemplateCrud<GET,ID,UPDATE,SAVE> {

	List<GET> getAll();
	
	GET getById(ID id);
	
	Integer update(UPDATE t);
	
	Integer save(SAVE t);
	
}
