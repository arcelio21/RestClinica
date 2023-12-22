package com.example.mapper.speciality;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.speciality.TuserSpeciality;

@Mapper
public interface MapperUserSpeciality {

	@Results(
		id="userSpecRes",
		value = {
			@Result(column = "id",property = "id"),
			@Result(column = "nameSpeciality",property = "specialityId.name"),
			@Result(column = "nameStatus",property = "statusId.name"),
			@Result(column = "nameUser",property = "userTypeRegId.userRegId.name"),
			@Result(column = "lastNameUser",property = "userTypeRegId.userRegId.lastName"),
			@Result(column = "nameTypeUser",property = "userTypeRegId.typeUser.nameTypeUser")
		}
	)
	@Select("SELECT us.id AS id, s.name AS nameSpeciality, st.name_status AS nameStatus,usr.name AS nameUser,usr.last_name AS lastNameUser, tu.name_type_user AS nameTypeUser" +
			"	FROM Tusers_specialties us " +
			"INNER JOIN Tspecialties s ON us.speciality_id = s.id " +
			"INNER JOIN Tusers_types_regs utr ON us.user_type_reg_id = utr.id " +
			"INNER JOIN TusersRegs usr ON utr.user_reg_id = usr.id " +
			"INNER JOIN Tstatus st ON us.status_id = st.id " +
			"INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id")
	List<TuserSpeciality> getAll();
	
	@ResultMap(value = "userSpecRes")
	@Select("SELECT us.id AS id, s.name AS nameSpeciality, st.name_status AS nameStatus,usr.name AS nameUser,usr.last_name AS lastNameUser, tu.name_type_user AS nameTypeUser" +
			"	FROM Tusers_specialties us " +
			"INNER JOIN Tspecialties s ON us.speciality_id = s.id " +
			"INNER JOIN Tusers_types_regs utr ON us.user_type_reg_id = utr.id " +
			"INNER JOIN TusersRegs usr ON utr.user_reg_id = usr.id " +
			"INNER JOIN Tstatus st ON us.status_id = st.id " +
			"INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id " +
			"WHERE us.id=#{id}")
	TuserSpeciality getById(@Param("id") Integer id);

	@Results(
			value = {
					@Result(column = "id",property = "id"),
					@Result(column = "nameStatus",property = "statusId.name"),
					@Result(column = "nameUser",property = "userTypeRegId.userRegId.name"),
					@Result(column = "lastNameUser",property = "userTypeRegId.userRegId.lastName"),
					@Result(column = "nameTypeUser",property = "userTypeRegId.typeUser.nameTypeUser")
			}
	)
	@Select("SELECT us.id AS id, st.name_status AS nameStatus,usr.name AS nameUser,usr.last_name AS lastNameUser, tu.name_type_user AS nameTypeUser" +
			"	FROM Tusers_specialties us " +
			"INNER JOIN Tusers_types_regs utr ON us.user_type_reg_id = utr.id " +
			"INNER JOIN TusersRegs usr ON utr.user_reg_id = usr.id " +
			"INNER JOIN Tstatus st ON us.status_id = st.id " +
			"INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id " +
			"WHERE us.speciality_id=#{idSpeciality}")
	List<TuserSpeciality> getByIdSpeciality(@Param("idSpeciality") Integer idSpeciality);


	@Results(
			value = {
					@Result(column = "id",property = "id"),
					@Result(column = "nameSpeciality",property = "specialityId.name"),
					@Result(column = "nameStatus",property = "statusId.name")
			}
	)
	@Select("SELECT us.id AS id, s.name AS nameSpeciality, st.name_status AS nameStatus" +
			"	FROM Tusers_specialties us " +
			"INNER JOIN Tspecialties s ON us.speciality_id = s.id " +
			"INNER JOIN Tstatus st ON us.status_id = st.id " +
			"WHERE us.user_type_reg_id=#{idUserTypeReg}")
	TuserSpeciality getByIdUserTypeReg(@Param("idUserTypeReg") Integer idUserTypeReg);
	

	@Results(
			id = "statusResult",
			value = {
					@Result(column = "id",property = "id"),
					@Result(column = "nameSpeciality",property = "specialityId.name"),
					@Result(column = "nameUser",property = "userTypeRegId.userRegId.name"),
					@Result(column = "lastNameUser",property = "userTypeRegId.userRegId.lastName"),
					@Result(column = "nameTypeUser",property = "userTypeRegId.typeUser.nameTypeUser")
			}
	)
	@Select("SELECT us.id AS id, s.name AS nameSpeciality, usr.name AS nameUser,usr.last_name AS lastNameUser, tu.name_type_user AS nameTypeUser" +
			"	FROM Tusers_specialties us " +
			"INNER JOIN Tspecialties s ON us.speciality_id = s.id " +
			"INNER JOIN Tusers_types_regs utr ON us.user_type_reg_id = utr.id " +
			"INNER JOIN TusersRegs usr ON utr.user_reg_id = usr.id " +
			"INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id " +
			"WHERE us.status_id=#{idStatus}")
	List<TuserSpeciality> getByIdStatus(@Param("idStatus") Integer idStatus);

	@ResultMap(value = "statusResult")
	@Select("SELECT us.id AS id, s.name AS nameSpeciality, usr.name AS nameUser,usr.last_name AS lastNameUser, tu.name_type_user AS nameTypeUser" +
			"	FROM Tusers_specialties us " +
			"INNER JOIN Tspecialties s ON us.speciality_id = s.id " +
			"INNER JOIN Tusers_types_regs utr ON us.user_type_reg_id = utr.id " +
			"INNER JOIN TusersRegs usr ON utr.user_reg_id = usr.id " +
			"INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id " +
			"WHERE us.status_id=1")
	List<TuserSpeciality> getByIdStatusActivated();

	@Results(
			value = {
					@Result(column = "id",property = "id"),
					@Result(column = "nameSpeciality",property = "specialityId.name"),
					@Result(column = "nameStatus",property = "statusId.name"),
					@Result(column = "nameUser",property = "userTypeRegId.userRegId.name"),
					@Result(column = "lastNameUser",property = "userTypeRegId.userRegId.lastName")
			}
	)
	@Select("SELECT us.id AS id, s.name AS nameSpeciality, st.name_status AS nameStatus,usr.name AS nameUser,usr.last_name AS lastNameUser " +
			"	FROM Tusers_specialties us " +
			"INNER JOIN Tspecialties s ON us.speciality_id = s.id " +
			"INNER JOIN Tusers_types_regs utr ON us.user_type_reg_id = utr.id " +
			"INNER JOIN TusersRegs usr ON utr.user_reg_id = usr.id " +
			"INNER JOIN Tstatus st ON us.status_id = st.id " +
			"INNER JOIN Ttypes_users tu ON utr.type_user_id = tu.id " +
			"WHERE tu.id=#{idTypeUser}")
	List<TuserSpeciality> getByTypeUserId(@Param("idTypeUser") Integer idTypeUser);
	
	@Update("UPDATE Tusers_specialties "
			+ "SET speciality_id=#{userSpec.specialityId.id}, user_type_reg_id=#{userSpec.userTypeRegId.id}, status_id=#{userSpec.statusId.id} "
			+ "WHERE id=#{userSpec.id}")
	Integer update(@Param("userSpec") TuserSpeciality tuserSpeciality);
	
	
	@Insert("INSERT INTO Tusers_specialties "
			+ "(speciality_id, user_type_reg_id, status_id) "
			+ "VALUES (#{userSpec.specialityId.id}, #{userSpec.userTypeRegId.id}, #{userSpec.statusId.id})")
	Integer save(@Param("userSpec") TuserSpeciality tuserSpeciality);
}
