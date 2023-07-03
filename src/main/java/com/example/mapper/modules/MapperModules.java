package com.example.mapper.modules;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.modules.Tmodule;

@Mapper
public interface MapperModules {

	@Select("SELECT * FROM Tmodules")
	@Result(column = "name_modules",property = "nameModule")
	public List<Tmodule> getAll();
	
	@Select("SELECT * FROM Tmodules WHERE id=#{id}")
	@Result(column = "name_modules",property = "nameModule")
	public Tmodule getById(@Param("id") Long id);
	
	@Update("UPDATE Tmodules SET name_modules=#{modul.nameModule} WHERE id=#{modul.id}")
	public Integer update(@Param("modul") Tmodule module);
	
	@Insert("INSERT INTO Tmodules (name_modules) VALUES(#{modul.nameModule})")
	public Integer insert(@Param("modul") Tmodule module);
}
