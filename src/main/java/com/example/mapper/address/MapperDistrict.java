package com.example.mapper.address;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
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
import com.example.entity.address.Tprovince;

@Mapper
public interface MapperDistrict {

	@Select("SELECT id,name, province_id FROM Tdistricts")
	@Results(id = "all", value = {
			@Result(column = "id", property = "id"),
			@Result(column = "name", property = "name"),
			@Result(column = "province_id", property = "province.id")
	})
	public List<Tdistrict> getAll();

	@Select("SELECT id,name, province_id FROM Tdistricts WHERE id=#{id}")
	@ResultMap(value = "all")
	public  Tdistrict getById(@Param("id") Integer id);

	@Select("SELECT id,name FROM Tdistricts")
	@Results(id = "allIdName", value = {
			@Result(column = "id", property = "id"),
			@Result(column = "name", property = "name")
	})
	public List<Tdistrict> getAllIdName();

	@Select("SELECT id,name FROM Tdistricts WHERE id=#{id}")
	@ResultMap(value = "allIdName")
	public Tdistrict getByIdName(@Param("id") Integer id);


	@Select("SELECT id,name FROM Tdistricts WHERE province_Id=#{prov.id}")
	@ResultMap(value = "allIdName")
	List<Tdistrict> getByProvinceId(@Param("prov") Tprovince tprovince);
	
	
	@Select("SELECT Tdistricts.id,Tdistricts.name,Tprovinces.id as provId,Tprovinces.name as provName FROM Tdistricts  "
			+ "INNER JOIN Tprovinces  ON Tdistricts.province_id=Tprovinces.id WHERE Tdistricts.id=#{id}")
	@Results(id="districtAllSimple",value=
			{@Result(column = "id",property = "id"),
			@Result(column = "name",property = "name"),
			@Result(column = "provId",property = "province.id"),
			@Result(column = "provName",property = "province.name")}
	)
	public Tdistrict getDistrictAndProvinceById(@Param("id") Integer id);
	
	
	@Insert("INSERT INTO Tdistricts (name) VALUES(#{dist.name})")
	public int save(@Param("dist")Tdistrict district);
	
	@Update("UPDATE Tdistricts SET name=#{dist.name} WHERE id=#{dist.id}")
	public int update(@Param("dist") Tdistrict district);
	
	
}
