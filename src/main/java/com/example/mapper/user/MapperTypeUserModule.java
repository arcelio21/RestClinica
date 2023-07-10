package com.example.mapper.user;

import com.example.entity.user.TtypeUserModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

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

	/**
	 * Obtener los modulos a los que tiene algun tipo de privilegio un tipo de usuario
	 * @param idTypeUser
	 * @return
	 */
	@Select("""
	SELECT DISTINCT Tm.name_modules as nameModule, Tm.id as idModule
		 FROM Ttypeusers_modules Ttm
				INNER JOIN Tmodules_privileges Tp on Ttm.modls_privgs_id = Tp.id
				INNER JOIN Tmodules Tm on Tp.module_id = Tm.id
				INNER JOIN Ttypes_users Tu on Ttm.type_user_id = Tu.id
				WHERE Ttm.type_user_id=#{idTypeUser} AND Tp.status_id=1
	""")
	@Results(value = {
			@Result(column = "nameModule", property = "modulePrivilegeId.module.nameModule"),
			@Result(column = "idModule", property = "modulePrivilegeId.module.id")
	})
	List<TtypeUserModule> getModuleDistinctByIdTypeUserAndStatusActived(@Param("idTypeUser") Integer idTypeUser);

	@SelectProvider(type = TypeUserModuleProviderSql.class,method = "getModuleDistinctByIdTypeUserAndIdStatus")
	@Results(value = {
			@Result(column = "nameModule", property = "modulePrivilegeId.module.nameModule"),
			@Result(column = "idModule", property = "modulePrivilegeId.module.id")
	})
	List<TtypeUserModule> getModuleDistinctByIdTypeUserAndIdStatus(@Param("idTypeUser") Integer idTypeUser, @Param("idStatus") Integer idStatus);

	@Select("""
	SELECT DISTINCT Tu.name_type_user AS typeUser, Tu.id AS idTypeUser
		FROM Ttypeusers_modules Ttm
				 INNER JOIN Tmodules_privileges Tp on Ttm.modls_privgs_id = Tp.id
				 INNER JOIN Tmodules Tm on Tp.module_id = Tm.id
				 INNER JOIN Tstatus Ts on Tp.status_id = Ts.id
				 INNER JOIN Ttypes_users Tu on Ttm.type_user_id = Tu.id
					WHERE  Tp.module_id=#{idModule} AND Tp.status_id=1
	""")
	@Results(value = {
			@Result(column = "typeUser", property = "typeUser.nameTypeUser"),
			@Result(column = "idTypeUser", property = "typeUser.id")
	})
	List<TtypeUserModule> getTypeUserDistinctByIdModuleAndStatusActivated( @Param("idModule") Long idModule);

	/**
	 * Obtener los tipos de usuarios que pueden tienen algun privilegio sobre un modulo
	 * @param idModule
	 * @return List<TtypeUserModule>
	 */
	@SelectProvider(type = TypeUserModuleProviderSql.class, method = "getTypeUserDistinctByIdModuleAndIdStatus")
	@Results(value = {
			@Result(column = "typeUser", property = "typeUser.nameTypeUser"),
			@Result(column = "idTypeUser", property = "typeUser.id")
	})
	List<TtypeUserModule> getTypeUserDistinctByIdModuleAndIdStatus( @Param("idModule") Long idModule,@Param("idStatus") Integer idStatus);

	/**
	 * METODO QUE RECIBE EL ID DEL TIPO DE USUARIO, Y DEVUELVE LOS PRIVILEGIOS
	 * QUE TIENE ESE TIPO DE USUARIO
	 * @param ttypeUserModule
	 * @return List<TtypeUserModule>
	 */
	@Select("""
	SELECT Tpr.id as idPrivilege, Tpr.name_privilege as namePrivilege,Ts.id as idStatus,Ts.name_status as nameStatus
		FROM Ttypeusers_modules tpm
			 INNER JOIN Tmodules_privileges Tp on tpm.modls_privgs_id = Tp.id
			 INNER JOIN Tprivileges Tpr on Tp.privilege_id = Tpr.id
			 INNER JOIN Tstatus Ts on Tp.status_id = Ts.id
			 WHERE tpm.type_user_id =#{ids.typeUser.id} AND Tp.module_id=#{ids.modulePrivilegeId.module.id} AND Ts.id=1
	""")
	@Results(value = {
			@Result(column = "idPrivilege", property = "modulePrivilegeId.privilege.id"),
			@Result(column = "namePrivelege", property = "modulePrivilegeId.privilege.namePrivilege"),
			@Result(column = "idStatus", property = "modulePrivilegeId.status.id"),
			@Result(column = "nameStatus", property = "modulePrivilegeId.status.name")
	})
	List<TtypeUserModule> getPrivelegeOfModuleByIdTypeUserAndIdModuleAndStatusActived(@Param("ids") TtypeUserModule ttypeUserModule);

	@Select("""
		SELECT  Tu.name_type_user AS typeUser,Ts.name_status AS nameStatus, Tp.name_privilege AS namePrivileg, Tm.name_modules AS nameModule
				FROM Ttypeusers_modules Ttm
				   INNER JOIN Tmodules_privileges Tmp on Ttm.modls_privgs_id = Tmp.id
				   INNER JOIN Tmodules Tm on Tmp.module_id = Tm.id
				   INNER JOIN Tstatus Ts on Tmp.status_id = Ts.id
				   INNER JOIN Ttypes_users Tu on Ttm.type_user_id = Tu.id
				   INNER JOIN Tprivileges Tp on Tmp.privilege_id = Tp.id
				   WHERE  Ttm.type_user_id=#{idTypeUser} AND Tmp.status_id=1;
	""")
	List<TtypeUserModule> getTypeModulePrivilegeByidTypeUserAndStatusActived(@Param("idTypeUser") Integer idTypeUser);
}
