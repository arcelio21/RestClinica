package com.example.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.user.TtypeUserModule;

@Mapper
public interface MapperTypeUserModule {

	@Select("SELECT * FROM Ttypeusers_modules")
	@Results(id = "typeUserModuleMap",
		value = {
			@Result(column = "modls_privgs_id",property = "modulePrivilegeId.id"),
			@Result(column = "type_user_id",property = "typeUser.id")
		}
	)
	List<TtypeUserModule> getAll();
	
	/**
	 * METODO QUE RECIBE EL ID DEL TIPO DE USUARIO, Y DEVUELVE LOS PRIVILEGIOS 
	 * QUE TIENE ESE TIPO DE USUARIO
	 * @param Integer (typeUserId)
	 * @return List<TtypeUserModule>
	 */
	@Select("SELECT * FROM Ttypeusers_modules WHERE type_user_id=#{id} ")
	@ResultMap(value = "typeUserModuleMap")
	List<TtypeUserModule> getPrivTypeUser(@Param("id") Integer typeUserId);
	
	/**
	 * METODO QUE RECIBE EL ID DE LOS PRIVLEGIOS DE UN MODULO, Y DEVOLVERA LOS TIPOS DE USUARIOS 
	 * QUE TENDRAN ACCESO A ESTE MODULO
	 * @param Integer(modulePrivilegeId)
	 * @return List<TtypeUserModule>
	 */
	@Select("SELECT * FROM Ttypeusers_modules WHERE modls_privgs_id=#{id}")
	@ResultMap(value = "typeUserModuleMap")
	List<TtypeUserModule> getModulePriv(@Param("id") Integer modulePrivilegeId);
	
	
	
	@Update("UPDATE Ttypeusers_modules "
			+ "SET modls_privgs_id=#{typeUserModule.modulePrivilegeId.id} "
			+ "WHERE type_user_id=#{typeUserModule.typeUser.id} AND modls_privgs_id=#{modulPrivBef}")
	Integer update(@Param("typeUserModule") TtypeUserModule ttypeUserModule, @Param("modulPrivBef") Integer modulPrivBef);
	
	
	
	@Insert("INSERT INTO Ttypeusers_modules "
			+ "(modls_privgs_id,type_user_id) "
			+ "VALUES (#{typeUserModule.modulePrivilegeId.id},#{typeUserModule.typeUser.id})")
	Integer save(@Param("typeUserModule") TtypeUserModule ttypeUserModule);
	
}
