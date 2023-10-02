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

	@Select("""
		SELECT utr.id AS ID, ur.name AS Name, ur.last_name AS LastName, ur.iden_card AS Identification,tu.name_type_user as TypeUser, 
			st.name_status AS Status
								FROM Tusers_types_regs utr
								INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
								INNER JOIN TusersRegs ur ON utr.user_reg_id = ur.id
								INNER JOIN Tstatus st ON utr.status_id = st.id
	""")
	@Results(id = "userTypeRegMap",
		value = {
				@Result(column = "ID",property = "id"),
				@Result(column = "Name",property = "userRegId.name"),
				@Result(column = "LastName",property = "userRegId.lastName"),
				@Result(column = "Identification",property = "userRegId.idenCard"),
				@Result(column = "TypeUser",property = "typeUser.nameTypeUser"),
				@Result(column = "Status",property = "statusId.name")
		}
	)
	List<TuserTypeReg> getAll();

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

	@Select("""
	 SELECT utr.id AS ID,tu.name_type_user as TypeUser, st.name_status AS Estado
		 FROM Tusers_types_regs utr
		 INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
		 INNER JOIN Tstatus st ON utr.status_id = st.id
	 WHERE utr.user_reg_id = #{id}
	""")
	@Results({
		@Result(column = "ID", property = "id"),
		@Result(column = "TypeUser",property = "typeUser.nameTypeUser"),
		@Result(column = "Status",property = "statusId.name")
	})
	List<TuserTypeReg> getByIdUserReg(@Param("id") Long id);

	@Select("""
		SELECT utr.id AS ID,tu.name_type_user as TypeUser, st.name_status AS Estado
		FROM Tusers_types_regs utr
					INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
					INNER JOIN Tstatus st ON utr.status_id = st.id
		WHERE utr.user_reg_id =#{id} AND utr.status_id=1 
	""")
	@Results({
		@Result(column = "ID", property = "id"),
		@Result(column = "TypeUser",property = "typeUser.nameTypeUser"),
		@Result(column = "Status",property = "statusId.name")
	})
	List<TuserTypeReg> getByIdUserRegActivated(@Param("id") Long id);
	
	@Select("""
		SELECT utr.id AS ID, ur.name AS Name, ur.last_name AS LastName, ur.iden_card AS Identification, 
			st.name_status AS Status
								FROM Tusers_types_regs utr
								INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id
								INNER JOIN TusersRegs ur ON utr.user_reg_id = ur.id
								INNER JOIN Tstatus st ON utr.status_id = st.id
		WHERE utr.type_user_id=#{idTypeUser}
	""")
	@Results(
		value = {
				@Result(column = "ID",property = "id"),
				@Result(column = "Name",property = "userRegId.name"),
				@Result(column = "LastName",property = "userRegId.lastName"),
				@Result(column = "Identification",property = "userRegId.idenCard"),
				@Result(column = "Status",property = "statusId.name")
		}
	)
	List<TuserTypeReg> getByIdTypeUser(@Param("idTypeUser") Integer id);
	
	@Select("SELECT * FROM 	Tusers_types_regs WHERE status_id=#{id}")
	@ResultMap("userTypeRegMap")
	List<TuserTypeReg> getByIdStatus(@Param("id") Integer id);
	
	@Update("UPDATE Tusers_types_regs "
			+ "SET user_reg_id=#{user.userRegId.id}, type_user_id=#{user.typeUser.id}, status_id=#{user.statusId.id} "
			+ "WHERE id=#{user.id} ")
	Integer update(@Param("user") TuserTypeReg tuserTypeReg);
	
	@Insert("INSERT INTO Tusers_types_regs "
			+ "(user_reg_id,type_user_id,status_id) "
			+ "VALUES (#{user.userRegId.id},#{user.typeUser.id},#{user.statusId.id})")
	Integer save(@Param("user") TuserTypeReg tuserTypeReg);
	
	
}
