package com.example.mapper.visit;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.visit.Tdiagnose;

@Mapper
public interface MapperDiagnoses {

	@Select("SELECT * FROM Tdiagnoses")
	List<Tdiagnose> getAll();
	
	@Select("SELECT * FROM Tdiagnoses WHERE id=#{id}")
	Tdiagnose getById(@Param("id") Integer id);
	
	@Update("UPDATE Tdiagnoses "
			+ "SET name=#{diag.name}"
			+ " WHERE id=#{diag.id}")
	Integer update(@Param("diag") Tdiagnose tdiagnose);
	
	@Insert("INSERT INTO Tdiagnoses "
			+ "(name) "
			+ "VALUES (#{diag.name})")
	Integer save(@Param("diag") Tdiagnose tdiagnose);
}
