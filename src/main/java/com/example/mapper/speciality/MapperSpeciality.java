package com.example.mapper.speciality;

import java.util.List;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.speciality.Tspeciality;

@Mapper
public interface MapperSpeciality {

	
	@ConstructorArgs(
		value = {
			@Arg(column = "id",id = true,javaType = Integer.class),
			@Arg(column = "name",javaType = String.class)
		}
	)
	@Select("SELECT * FROM Tspecialties")
	List<Tspeciality> getAll();
	
	@ConstructorArgs(
		value = {
			@Arg(column = "id",id = true,javaType = Integer.class),
			@Arg(column = "name",javaType = String.class)
		}
	)
	@Select("SELECT * FROM Tspecialties WHERE id=#{id}")
	public Tspeciality getById(@Param("id") Integer id);
	
	@Update("UPDATE Tspecialties SET name=#{spec.name} WHERE id=#{spec.id}")
	public int update(@Param("spec") Tspeciality tspeciality);
	
	@Insert("INSERT INTO Tspecialties (name) VALUES(#{spec.name})")
	public int save(@Param("spec") Tspeciality tspeciality);
}
