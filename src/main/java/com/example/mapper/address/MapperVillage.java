package com.example.mapper.address;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tvillage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MapperVillage {

	@Select("SELECT id,name, district_id FROM Tvillages")
	@Results(
			id = "villageSimple",
			value = {
					@Result(column = "id", property = "id"),
					@Result(column = "name", property = "name"),
					@Result(column = "district_id", property = "district.id")
			}
	)
	List<Tvillage> getAll();

	@Select("SELECT id,name FROM Tvillages WHERE id=#{id}")
	@ResultMap(value = "villageSimple")
	Tvillage getById(@Param("id") Integer id);

	@Select("SELECT id,name FROM Tvillages")
	@Results(
			id = "village",
			value = {
					@Result(column = "id", property = "id"),
					@Result(column = "name", property = "name")
			}
	)
	List<Tvillage> getAllIdName();

	@Select("SELECT * FROM Tvillages WHERE id=#{id}")
	@ResultMap(value = "village")
	Tvillage getByIdName(@Param("id") Integer id);
	
	@Select("SELECT id as id,name as name FROM Tvillages WHERE district_id=#{dist.id}")
	@ResultMap(value = "village")
	List<Tvillage> getByDistrictId(@Param("dist") Tdistrict tdistrict);
	
	
	@Select("SELECT Tvillages.id,Tvillages.name,Tdistricts.id as distId,Tdistricts.name as distName FROM Tvillages  "
			+ "INNER JOIN Tdistricts  ON Tvillages.district_id=Tdistricts.id WHERE Tvillages.id=#{id}")
	@Results(id="districtInner",value=
			{@Result(column = "id",property = "id"),
			@Result(column = "name",property = "name"),
			@Result(column = "distId",property = "district.id"),
			@Result(column = "distName",property = "district.name")}
	)
	Tvillage getDistrictAllById(@Param("id") Integer id);
	
	
	@Insert("INSERT INTO Tvillages (name) VALUES(#{vill.name})")
	int save(@Param("vill")Tvillage district);
	
	@Update("UPDATE Tvillages SET name=#{vill.name} WHERE id=#{vill.id}")
	int update(@Param("vill") Tvillage district);
}
