package com.example.mapper.user;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.user.TtypeUser;

@Mapper
public interface MapperTypeUser {

	@Select("SELECT * FROM Ttypes_users")
	@Result(column = "name_type_user",property = "nameTypeUser")
	@MapKey(value = "id")
	public Map<Integer,TtypeUser> getAll();
	
	@Select("SELECT * FROM Ttypes_users WHERE id=#{id}")
	@Result(column = "name_type_user",property = "nameTypeUser")
	public TtypeUser getById(@Param("id") Integer id);
	
	@Update("UPDATE Ttypes_users SET name_type_user=#{typeUser.nameTypeUser} WHERE id=#{typeUser.id}")
	public int update(@Param("typeUser") TtypeUser typeUser);
	
	@Insert("INSERT INTO Ttypes_users (name_type_user) VALUES(#{typeUser.nameTypeUser})")
	public int save(@Param("typeUser") TtypeUser typeUser);
}
