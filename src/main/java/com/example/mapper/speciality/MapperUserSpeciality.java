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
			@Result(column = "speciality_id",property = "specialityId.id"),
			@Result(column = "user_type_reg_id",property = "userTypeRegId.id"),
			@Result(column = "status_id",property = "statusId.id")
		}
	)
	@Select("SELECT * FROM Tusers_specialties")
	List<TuserSpeciality> getAll();
	
	@ResultMap(value = "userSpecRes")
	@Select("SELECT * FROM Tusers_specialties WHERE id=#{id}")
	TuserSpeciality getById(@Param("id") Integer id);
	
	@ResultMap(value = "userSpecRes")
	@Select("SELECT * FROM Tusers_specialties WHERE speciality_id=#{id}")
	List<TuserSpeciality> getByIdSpeciality(@Param("id") Integer idSpeciality);
	
	@ResultMap(value = "userSpecRes")
	@Select("SELECT * FROM Tusers_specialties WHERE user_type_reg_id=#{id}")
	TuserSpeciality getByIdUserTypeReg(@Param("id") Integer idUserTypeReg);
	
	@ResultMap(value = "userSpecRes")
	@Select("SELECT * FROM Tusers_specialties WHERE status_id=#{id}")
	List<TuserSpeciality> getByIdStatus(@Param("id") Integer idStatus);
	
	@Update("UPDATE Tusers_specialties "
			+ "SET speciality_id=#{userSpec.specialityId.id}, user_type_reg_id=#{userSpec.userTypeRegId.id}, status_id=#{userSpec.statusId.id} "
			+ "WHERE id=#{userSpec.id}")
	Integer update(@Param("userSpec") TuserSpeciality tuserSpeciality);
	
	
	@Insert("INSERT INTO Tusers_specialties "
			+ "(speciality_id, user_type_reg_id, status_id) "
			+ "VALUES (#{userSpec.specialityId.id}, #{userSpec.userTypeRegId.id}, #{userSpec.statusId.id})")
	Integer save(@Param("userSpec") TuserSpeciality tuserSpeciality);
}
