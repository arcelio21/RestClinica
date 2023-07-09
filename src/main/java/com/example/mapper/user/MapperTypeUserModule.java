package com.example.mapper.user;

import com.example.entity.user.TtypeUserModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface MapperTypeUserModule {

	/**
	 * Recupera una lista de TtypeUserModule que contiene información sobre los módulos, privilegios y estados asignados a cada tipo de usuario.
	 *
	 * @return Lista de TtypeUserModule con la información solicitada.
	 */
	@Select("SELECT Ttm.id, Tm.name_modules AS nameModule, Tu.name_type_user AS nameTypeUser, tpr.name_privilege AS namePrivilege " +
			"FROM Ttypeusers_modules Ttm " +
			"INNER JOIN Tmodules_privileges Tp on Ttm.modls_privgs_id = Tp.id " +
			"INNER JOIN Tmodules Tm on Tp.module_id = Tm.id " +
			"INNER JOIN Tprivileges tpr ON Tp.privilege_id = tpr.id " +
			"INNER JOIN Ttypes_users Tu on Ttm.type_user_id = Tu.id")
	@Results(id = "typeUserModuleMap",
			value = {
					@Result(column = "id", property = "id"),
					@Result(column = "nameModule",property = "modulePrivilegeId.module.nameModule"),
					@Result(column = "nameTypeUser",property = "typeUser.nameTypeUser"),
					@Result(column = "namePrivilege", property = "modulePrivilegeId.privilege.namePrivilege")
			}
	)
	List<TtypeUserModule> getAll();

	/**
	 * Recupera una lista de TtypeUserModule que contiene información sobre los módulos, privilegios y estados asignados a cada tipo de usuario.
	 * Filtrado Por id
	 * @param id
	 * @return Lista de TtypeUserModule con la información solicitada.
	 */
	@Select("SELECT Ttm.id, Tm.name_modules AS nameModule, Tu.name_type_user AS nameTypeUser, tpr.name_privilege AS namePrivilege " +
			"FROM Ttypeusers_modules Ttm " +
			"INNER JOIN Tmodules_privileges Tp on Ttm.modls_privgs_id = Tp.id " +
			"INNER JOIN Tmodules Tm on Tp.module_id = Tm.id " +
			"INNER JOIN Tprivileges tpr ON Tp.privilege_id = tpr.id " +
			"INNER JOIN Ttypes_users Tu on Ttm.type_user_id = Tu.id " +
			"WHERE Ttm.id = #{id}")
	@ResultMap(value = "typeUserModuleMap")
	TtypeUserModule getById(@Param("id") Long id);

	/**
	 * OBTENER LISTA DE MODULOS A LOS QUE LOS TIPOS DE USUARIOS TIENE PRIVILEGIOS
	 * @return TtypeUserModule
	 */
	@Select("""
	SELECT DISTINCT(Tm.name_modules) as nameModule,Tm.id as idModule, Tu.name_type_user as typeUser, Tu.id AS idTypeUser
		FROM Ttypeusers_modules Ttm
				INNER JOIN Tmodules_privileges Tp on Ttm.modls_privgs_id = Tp.id
				INNER JOIN Tmodules Tm on Tp.module_id = Tm.id
				INNER JOIN Ttypes_users Tu on Ttm.type_user_id = Tu.id
	""")
	@Results(id = "moduleAndTypeUserDistinct",
			value = {
					@Result(column = "nameModule", property = "modulePrivilegeId.module.nameModule"),
					@Result(column = "idModule", property = "modulePrivilegeId.module.id"),
					@Result(column = "typeUser", property = "typeUser.nameTypeUser"),
					@Result(column = "idTypeUser", property = "typeUser.id")
			}
	)
	List<TtypeUserModule> getModuleAndTypeUserDistinct();
	
}
