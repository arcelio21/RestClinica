package com.example.mapper.address;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tvillage;

@Mapper
public interface MapperVillage {

	@Select("SELECT * FROM Tvillages")
	@Results(id = "villageSimple",
			value={@Result(column = "id",property = "id"),
			@Result(column = "name",property = "name"),
			@Result(column="district_id",property = "district",one = @One(select = "com.example.mapper.address.MapperDistrict.getByIdSimple",
					fetchType = FetchType.LAZY))}
	)
	public List<Tvillage> getAll();
	
	@Select("SELECT * FROM Tvillages WHERE id=#{id}")
	@ResultMap(value = "villageSimple")
	public Tvillage getById(@Param("id") Integer id);
	
	@Select("SELECT id as id,name as name FROM Tvillages WHERE district_id=#{dist.id}")
	@Result(column = "id",property = "id")
	@Result(column = "name",property = "name")
	List<Tvillage> getByDistrictId(@Param("dist") Tdistrict tdistrict);
	
	
	@Select("SELECT Tvillages.id,Tvillages.name,Tdistricts.id as distId,Tdistricts.name as distName FROM Tvillages  "
			+ "INNER JOIN Tdistricts  ON Tvillages.district_id=Tdistricts.id WHERE Tvillages.id=#{id}")
	@Results(id="districtInner",value=
			{@Result(column = "id",property = "id"),
			@Result(column = "name",property = "name"),
			@Result(column = "distId",property = "district.id"),
			@Result(column = "distName",property = "district.name")}
	)
	public Tvillage getDistrictAllById(@Param("id") Integer id);
	
	
	@Insert("INSERT INTO Tvillages (name) VALUES(#{vill.name})")
	public int save(@Param("vill")Tvillage district);
	
	@Update("UPDATE Tvillages SET name=#{vill.name} WHERE id=#{vill.id}")
	public int update(@Param("vill") Tvillage district);
}