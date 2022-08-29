package com.example.mapper.address;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.address.Tdistrict;

@Mapper
public interface MapperDistrict {

	@Select("SELECT * FROM Tdistricts")
	@Results(id = "districtSimple",
			value={@Result(column = "id",property = "id"),
			@Result(column = "name",property = "name"),
			@Result(column="province_Id",property = "province.id")}
	)
	public List<Tdistrict> getAll();
	
	@Select("SELECT * FROM Tdistricts WHERE id=#{id}")
	@ResultMap(value = "districtSimple")
	public Tdistrict getById(@Param("id") Integer id);
	
	
	@Select("SELECT Tdistricts.id,Tdistricts.name,Tprovinces.id as provId,Tprovinces.name as provName FROM Tdistricts  "
			+ "INNER JOIN Tprovinces  ON Tdistricts.province_id=Tprovinces.id WHERE Tdistricts.id=#{id}")
	@Results(
			{@Result(column = "id",property = "id"),
			@Result(column = "name",property = "name"),
			@Result(column = "provId",property = "province.id"),
			@Result(column = "provName",property = "province.name")}
	)
	public Tdistrict getDistrictAllById(@Param("id") Integer id);
	
	
	@Insert("INSERT INTO Tdistricts (name) VALUES(#{dist.name})")
	public int save(@Param("dist")Tdistrict district);
	
	@Update("UPDATE Tdistricts SET name=#{dist.name} WHERE id=#{dist.id}")
	public int update(@Param("dist") Tdistrict district);
	
	
}
