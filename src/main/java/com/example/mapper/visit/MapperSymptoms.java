package com.example.mapper.visit;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.visit.Tsymptom;

@Mapper
public interface MapperSymptoms {

	@Select("SELECT * FROM Tsymptoms")
	List<Tsymptom> getAll();
	
	@Select("SELECT * FROM Tsymptoms WHERE id=#{id}")
	Tsymptom getById(@Param("id") Integer od);
	
	@Update("UPDATE Tsymptoms "
			+ "SET name=#{sym.name}"
			+ " WHERE id=#{sym.id}")
	Integer update(@Param("sym") Tsymptom tsymptom);
	
	@Insert("INSERT INTO Tsymptoms "
			+ "(name) "
			+ "VALUES (#{sym.name})")
	Integer save(@Param("sym") Tsymptom tsymptom);
}
