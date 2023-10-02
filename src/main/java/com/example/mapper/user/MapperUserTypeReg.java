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

import com.example.entity.user.TuserTypeReg;

@Mapper
public interface MapperUserTypeReg {

	/**
	 * Recupera una lista de registro con informacion de usuario y su tipo de
	 * usuario asociado.
	 * Este devolvera: ID, nombre, apellido, cedula, nombre del tipo de usuario,
	 * estado.
	 * 
	 * @return Una lista de objetos TuserTypeReg.
	 */
	@Select("""
				SELECT utr.id AS ID, ur.name AS Name, ur.last_name AS LastName, ur.iden_card AS Identification,tu.name_type_user as TypeUser,
					st.name_status AS Status
										FROM Tusers_types_regs utr
										INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
										INNER JOIN TusersRegs ur ON utr.user_reg_id = ur.id
										INNER JOIN Tstatus st ON utr.status_id = st.id
			""")
	@Results(id = "userTypeRegMap", value = {
			@Result(column = "ID", property = "id"),
			@Result(column = "Name", property = "userRegId.name"),
			@Result(column = "LastName", property = "userRegId.lastName"),
			@Result(column = "Identification", property = "userRegId.idenCard"),
			@Result(column = "TypeUser", property = "typeUser.nameTypeUser"),
			@Result(column = "Status", property = "statusId.name")
	})
	List<TuserTypeReg> getAll();

	/**
	 * Recupera un registro de usuario tipo-registro junto con información
	 * relacionada basado en su ID.
	 * Este método devolverá: ID, nombre, apellido, cédula, nombre del tipo de
	 * usuario y estado.
	 *
	 * @param id El ID del registro de usuario tipo-registro que se va a recuperar.
	 * @return Un objeto TuserTypeReg que contiene información sobre el usuario
	 *         tipo-registro, o null si no se encuentra.
	 */
	@Select("""
				SELECT utr.id AS ID, ur.name AS Name, ur.last_name AS LastName, ur.iden_card AS Identification,tu.name_type_user as TypeUser,
					st.name_status AS Status
										FROM Tusers_types_regs utr
										INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
										INNER JOIN TusersRegs ur ON utr.user_reg_id = ur.id
										INNER JOIN Tstatus st ON utr.status_id = st.id
										WHERE utr.id=#{id}
			""")
	@ResultMap("userTypeRegMap")
	TuserTypeReg getById(@Param("id") Long id);

	/**
	 * Recupera un registro de usuario tipo-registro asociado a un usuario
	 * registrado específico.
	 * Este método devuelve el ID, el nombre del tipo de usuario y el estado del
	 * registro de usuario tipo-registro.
	 *
	 * @param id El ID del usuario registrado para el cual se desea recuperar el
	 *           registro de usuario tipo-registro.
	 * @return Un objeto TuserTypeReg que contiene información sobre el registro de
	 *         usuario tipo-registro asociado, o null si no se encuentra.
	 */
	@Select("""
			 SELECT utr.id AS ID,tu.name_type_user as TypeUser, st.name_status AS Estado
				 FROM Tusers_types_regs utr
				 INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
				 INNER JOIN Tstatus st ON utr.status_id = st.id
			 WHERE utr.user_reg_id = #{id}
			""")
	@Results({
			@Result(column = "ID", property = "id"),
			@Result(column = "TypeUser", property = "typeUser.nameTypeUser"),
			@Result(column = "Status", property = "statusId.name")
	})
	List<TuserTypeReg> getByIdUserReg(@Param("id") Long id);

	/**
	 * Recupera una lista de registros de usuario tipo-registro activados asociados
	 * a un usuario registrado específico.
	 * Este método devuelve el ID, el nombre del tipo de usuario y el estado de los
	 * registros de usuario tipo-registro activados.
	 *
	 * @param id El ID del usuario registrado para el cual se desean recuperar los
	 *           registros activados.
	 * @return Una lista de objetos TuserTypeReg que contienen información sobre los
	 *         registros de usuario tipo-registro activados, o una lista vacía si no
	 *         se encuentran registros activados.
	 */
	@Select("""
				SELECT utr.id AS ID,tu.name_type_user as TypeUser, st.name_status AS Estado
				FROM Tusers_types_regs utr
							INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
							INNER JOIN Tstatus st ON utr.status_id = st.id
				WHERE utr.user_reg_id =#{id} AND utr.status_id=1
			""")
	@Results({
			@Result(column = "ID", property = "id"),
			@Result(column = "TypeUser", property = "typeUser.nameTypeUser"),
			@Result(column = "Status", property = "statusId.name")
	})
	List<TuserTypeReg> getByIdUserRegActivated(@Param("id") Long id);

	/**
	 * Recupera una lista de registros de usuario tipo-registro asociados a un tipo
	 * de usuario específico.
	 * Este método devuelve el ID, nombre, apellido, cédula y estado de los
	 * registros de usuario tipo-registro asociados al tipo de usuario.
	 *
	 * @param idTypeUser El ID del tipo de usuario para el cual se desean recuperar
	 *                   los registros de usuario tipo-registro.
	 * @return Una lista de objetos TuserTypeReg que contiene información sobre los
	 *         registros de usuario tipo-registro asociados al tipo de usuario, o
	 *         una lista vacía si no se encuentran registros asociados.
	 */
	@Select("""
				SELECT utr.id AS ID, ur.name AS Name, ur.last_name AS LastName, ur.iden_card AS Identification,
					st.name_status AS Status
										FROM Tusers_types_regs utr
										INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
										INNER JOIN TusersRegs ur ON utr.user_reg_id = ur.id
										INNER JOIN Tstatus st ON utr.status_id = st.id
				WHERE utr.type_user_id=#{idTypeUser}
			""")
	@Results(value = {
			@Result(column = "ID", property = "id"),
			@Result(column = "Name", property = "userRegId.name"),
			@Result(column = "LastName", property = "userRegId.lastName"),
			@Result(column = "Identification", property = "userRegId.idenCard"),
			@Result(column = "Status", property = "statusId.name")
	})
	List<TuserTypeReg> getByIdTypeUser(@Param("idTypeUser") Integer id);

	/**
	 * Recupera una lista de registros de usuario tipo-registro basados en su
	 * estado.
	 * Este método devuelve el ID, nombre, apellido, cédula, nombre del tipo de
	 * usuario y estado de los registros de usuario tipo-registro que tienen el
	 * estado especificado.
	 *
	 * @param idStatus El ID del estado para el cual se desean recuperar los
	 *                 registros de usuario tipo-registro.
	 * @return Una lista de objetos TuserTypeReg que contiene información sobre los
	 *         registros de usuario tipo-registro con el estado especificado, o una
	 *         lista vacía si no se encuentran registros con ese estado.
	 */
	@Select("""
				SELECT utr.id AS ID, ur.name AS Name, ur.last_name AS LastName, ur.iden_card AS Identification,tu.name_type_user as TypeUser,
					st.name_status AS Status
							FROM Tusers_types_regs utr
							INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
							INNER JOIN TusersRegs ur ON utr.user_reg_id = ur.id
				WHERE utr.status_id=#{idStatus}
			""")
	@ResultMap("userTypeRegMap")
	List<TuserTypeReg> getByIdStatus(@Param("idStatus") Integer id);

	/**
	 * Actualiza un registro de usuario tipo-registro en la base de datos.
	 *
	 * @param tuserTypeReg El objeto TuserTypeReg que contiene la información
	 *                     actualizada del registro de usuario tipo-registro.
	 * @return El número de filas afectadas por la actualización (debería ser 1 si
	 *         la actualización es exitosa).
	 */
	@Update("UPDATE Tusers_types_regs "
			+ "SET user_reg_id=#{user.userRegId.id}, type_user_id=#{user.typeUser.id}, status_id=#{user.statusId.id} "
			+ "WHERE id=#{user.id} ")
	Integer update(@Param("user") TuserTypeReg tuserTypeReg);

	/**
	 * Inserta un nuevo registro de usuario tipo-registro en la base de datos.
	 *
	 * @param tuserTypeReg El objeto TuserTypeReg que contiene la información del
	 *                     nuevo registro de usuario tipo-registro a ser insertado.
	 * @return El número de filas afectadas por la inserción (debería ser 1 si la
	 *         inserción es exitosa).
	 */
	@Insert("INSERT INTO Tusers_types_regs "
			+ "(user_reg_id,type_user_id,status_id) "
			+ "VALUES (#{user.userRegId.id},#{user.typeUser.id},#{user.statusId.id})")
	Integer save(@Param("user") TuserTypeReg tuserTypeReg);

}
