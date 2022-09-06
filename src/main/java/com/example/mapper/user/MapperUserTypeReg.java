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

	@Select("SELECT * FROM tusers_types_regs")
	@Results(id = "userTypeRegMap",
		value = {
				@Result(column = "id",property = "id"),
				@Result(column = "user_reg_id",property = "userRegId.id"),
				@Result(column = "type_user_id",property = "typeUser.id"),
				@Result(column = "status_id",property = "statusId.id")
		}
	)
	List<TuserTypeReg> getAll();
	
	@Select("SELECT * FROM tusers_types_regs WHERE id=#{id}")
	@ResultMap("userTypeRegMap")
	TuserTypeReg getById(@Param("id") Integer id);
	
	@Select("SELECT * FROM tusers_types_regs WHERE user_reg_id=#{id}")
	@ResultMap("userTypeRegMap")
	List<TuserTypeReg> getByIdUserReg(@Param("id") Integer id);
	
	@Select("SELECT * FROM tusers_types_regs WHERE type_user_id=#{id}")
	@ResultMap("userTypeRegMap")
	List<TuserTypeReg> getByIdTypeUser(@Param("id") Integer id);
	
	@Select("SELECT * FROM tusers_types_regs WHERE status_id=#{id}")
	@ResultMap("userTypeRegMap")
	List<TuserTypeReg> getByIdStatus(@Param("id") Integer id);
	
	@Update("UPDATE tusers_types_regs "
			+ "SET user_reg_id=#{user.userRegId.id}, type_user_id=#{user.typeUser.id}, status_id=#{user.statusId.id} "
			+ "WHERE id=#{user.id} ")
	Integer update(@Param("user") TuserTypeReg tuserTypeReg);
	
	@Insert("INSERT INTO tusers_types_regs "
			+ "(user_reg_id,type_user_id,status_id) "
			+ "VALUES (#{user.userRegId.id},#{user.typeUser.id},#{user.statusId.id})")
	Integer save(@Param("user") TuserTypeReg tuserTypeReg);
	
	
}
