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
	@Results(id = "userRegSimple",value= {
		@Result(column = "id",property = "id"),
		@Result(column = "iden_card",property = "idenCard"),
		@Result(column = "name",property = "name"),
		@Result(column = "last_name",property = "lastName"),
		@Result(column = "contact",property = "contact"),
		@Result(column = "email",property = "email"),
		@Result(column = "fecha_nacimiento",property = "fechaNacimiento"),
		@Result(column = "password",property = "password"),
		@Result(column = "fecha_creacion",property = "fechaCreacion"),
		@Result(column = "address_id",property = "addressId.id")
	})
	public List<TuserReg> getAll();
	
	
	@Select("SELECT * FROM TusersRegs WHERE id=#{id}")
	@ResultMap(value = "userRegSimple")
	public TuserReg getById(@Param("id") Integer id);

	@Select("SELECT * FROM TusersRegs WHERE iden_card=#{idencard}")
	@ResultMap(value = "userRegSimple")
	public Optional<TuserReg> getByIdenCard(@Param("idencard") Long idenCard);
	
	@Select("SELECT * FROM TusersRegs WHERE name LIKE '${name}%' ")
	@ResultMap(value = "userRegSimple")
	public List<TuserReg> getByName(@Param("name") String name);
	
	
	@Insert("INSERT INTO TusersRegs(iden_card,name,last_name,contact,email,fecha_nacimiento,password,address_id)"
			+ " VALUES(#{user.idenCard},#{user.name},#{user.lastName},#{user.contact},#{user.email},#{user.fechaNacimiento},"
			+ "#{user.password},#{user.addressId.id}) ")
	public Integer save(@Param("user") TuserReg user);
	
	
	@Update("UPDATE TusersRegs SET iden_card=#{user.idenCard},name=#{user.name},last_name=#{user.lastName},contact=#{user.contact},"
			+ "email=#{user.email},fecha_nacimiento=#{user.fechaNacimiento},address_id=#{user.addressId.id} WHERE id=#{user.id}")
	public Integer update(@Param("user") TuserReg user);
	
	
	@Update("UPDATE TusersRegs SET password=SHA1('${user.password}') WHERE id=${user.id} OR iden_card=${user.idenCard}")
	public Integer updatePassword(@Param("user") TuserReg user);
	
	
	@Select("SELECT * FROM TusersRegs WHERE iden_card=${user.idenCard} AND password=sha1('${user.password}') ")
	@Result(column = "id",property = "id")
	@Result(column = "iden_card",property = "idenCard")
	@Result(column = "name",property = "name")
	@Result(column = "last_name",property = "lastName")
	@Result(column = "contact",property = "contact")
	@Result(column = "email",property = "email")
	@Result(column = "fecha_nacimiento",property = "fechaNacimiento")
	@Result(column = "fecha_creacion",property = "fechaCreacion")
	@Result(column = "address_id",property = "addressId.id")
	public TuserReg validateAccount(@Param("user") TuserReg user);
}
