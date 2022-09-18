package com.example.mapper.visit;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.visit.Ttest;
import com.example.entity.visit.TvitalSign;

@Mapper
public interface MapperTest {
	
	@Select("SELECT * FROM Ttests")
	List<TvitalSign> getAll();
	
	@Select("SELECT * FROM Ttests WHERE id=#{id}")
	TvitalSign getById(@Param("id") Integer od);
	
	@Update("UPDATE Ttests "
			+ "SET name=#{test.name}"
			+ " WHERE id=#{test.id}")
	Integer update(@Param("test") Ttest ttest);
	
	@Insert("INSERT INTO Ttests "
			+ "(name) "
			+ "VALUES (#{test.name})")
	Integer save(@Param("test") Ttest ttest);
}
