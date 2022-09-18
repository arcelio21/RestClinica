package com.example.mapper.visit;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.visit.TvitalSign;

@Mapper
public interface MapperVitalSign {

	@Select("SELECT * FROM Tvital_signs")
	List<TvitalSign> getAll();
	
	@Select("SELECT * FROM Tvital_signs WHERE id=#{id}")
	TvitalSign getById(@Param("id") Integer od);
	
	@Update("UPDATE Tvital_signs "
			+ "SET name=#{vital.name}"
			+ " WHERE id=#{vital.id}")
	Integer update(@Param("vital") TvitalSign sign);
	
	@Insert("INSERT INTO Tvital_signs "
			+ "(name) "
			+ "VALUES (#{vital.name})")
	Integer save(@Param("vital") TvitalSign sign);
}
