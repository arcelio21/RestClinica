package com.example.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.entity.user.TtypeUser;

@Mapper
public interface MapperTypeUser {

	@Select("SELECT * FROM Ttypes_users")
	@Results(id = "resulTypeUser", value = {
			@Result(column = "id", property = "id"),
			@Result(column = "name_type_user",property = "nameTypeUser")
	})
	List<TtypeUser> getAll();
	
	@Select("SELECT * FROM Ttypes_users WHERE id=#{id}")
	@ResultMap(value = "resulTypeUser")
	TtypeUser getById(@Param("id") Integer id);
	
	@Update("UPDATE Ttypes_users SET name_type_user=#{typeUser.nameTypeUser} WHERE id=#{typeUser.id}")
	int update(@Param("typeUser") TtypeUser typeUser);
	
	@Insert("INSERT INTO Ttypes_users (name_type_user) VALUES(#{typeUser.nameTypeUser})")
	int save(@Param("typeUser") TtypeUser typeUser);
}
