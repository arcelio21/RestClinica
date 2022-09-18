package com.example.mapper.visit;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.visit.Tdiagnose;
import com.example.entity.visit.TvitalSign;

@Mapper
public interface MapperDiagnoses {

	@Select("SELECT * FROM Tdiagnoses")
	List<TvitalSign> getAll();
	
	@Select("SELECT * FROM Tdiagnoses WHERE id=#{id}")
	TvitalSign getById(@Param("id") Integer od);
	
	@Update("UPDATE Tdiagnoses "
			+ "SET name=#{diag.name}"
			+ " WHERE id=#{diag.id}")
	Integer update(@Param("diag") Tdiagnose ttest);
	
	@Insert("INSERT INTO Tdiagnoses "
			+ "(name) "
			+ "VALUES (#{diag.name})")
	Integer save(@Param("diag") Tdiagnose ttest);
}
