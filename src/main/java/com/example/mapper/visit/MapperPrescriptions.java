package com.example.mapper.visit;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.visit.Tprescription;
import com.example.entity.visit.TvitalSign;

@Mapper 
public interface MapperPrescriptions {

	
	@Select("SELECT * FROM Tprescriptions")
	List<Tprescription> getAll();
	
	@Select("SELECT * FROM Tprescriptions WHERE id=#{id}")
	TvitalSign getById(@Param("id") Integer od);
	
	@Update("UPDATE Tprescriptions "
			+ "SET name=#{pre.name}"
			+ " WHERE id=#{pre.id}")
	Integer update(@Param("pre") Tprescription tprescription);
	
	@Insert("INSERT INTO Tprescriptions "
			+ "(name) "
			+ "VALUES (#{pre.name})")
	Integer save(@Param("pre") Tprescription tprescription);
}
