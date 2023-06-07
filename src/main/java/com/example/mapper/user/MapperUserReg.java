package com.example.mapper.user;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.user.TuserReg;

@Mapper
public interface MapperUserReg {


	@Select("SELECT * FROM TusersRegs")
	@Results(id = "userRegSimple", value = {
			@Result(column = "id", property = "id"),
			@Result(column = "iden_card", property = "idenCard"),
			@Result(column = "name", property = "name"),
			@Result(column = "last_name", property = "lastName"),
			@Result(column = "contact", property = "contact"),
			@Result(column = "email", property = "email"),
			@Result(column = "birthday", property = "birthday"),
			@Result(column = "creation_date", property = "creationDate"),
			@Result(column = "address_id", property = "addressId.id")
	})
	List<TuserReg> getAll();


	@Select("SELECT * FROM TusersRegs WHERE id=#{id}")
	@ResultMap(value = "userRegSimple")
	TuserReg getById(@Param("id") Long id);

	@Select("SELECT * FROM TusersRegs WHERE iden_card=#{idencard}")
	@ResultMap(value = "userRegSimple")
	Optional<TuserReg> getByIdenCard(@Param("idencard") Long idenCard);

	@Select("SELECT * FROM TusersRegs WHERE name LIKE '${name}%' ")
	@ResultMap(value = "userRegSimple")
	List<TuserReg> getByName(@Param("name") String name);
	
	
	@Insert("INSERT INTO TusersRegs(iden_card,name,last_name,contact,email,birthday,password,address_id)"
			+ " VALUES(#{user.idenCard},#{user.name},#{user.lastName},#{user.contact},#{user.email},#{user.birthday},"
			+ "#{user.password},#{user.addressId.id}) ")
	Integer save(@Param("user") TuserReg user);
	
	
	@Update("UPDATE TusersRegs SET iden_card=#{user.idenCard},name=#{user.name},last_name=#{user.lastName},contact=#{user.contact},"
			+ "email=#{user.email},birthday=#{user.birthday},address_id=#{user.addressId.id} WHERE id=#{user.id}")
	Integer update(@Param("user") TuserReg user);
	
	
	@Update("UPDATE TusersRegs SET password='${newPass}' WHERE iden_card=${user.idenCard} AND password=${user.password} ")
	Integer updatePassword(@Param("user") TuserReg user, @Param("newPass") String newPass);

}
