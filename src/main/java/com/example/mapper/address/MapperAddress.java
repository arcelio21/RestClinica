package com.example.mapper.address;

import com.example.entity.address.Taddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MapperAddress {

	@Select("SELECT a.id as id,v.id vid, v.name as vname, d.name as dname, p.name as pname, specific_address " +
			"FROM Taddress a INNER JOIN Tvillages v ON a.village_id=v.id " +
			"INNER JOIN Tdistricts d ON v.district_id=d.id " +
			"INNER JOIN Tprovinces p ON d.province_id=p.id")
	@Results(
			id = "address", value = {
			@Result(column = "id", property = "id"),
			@Result(column = "vid", property = "villageId.id"),
			@Result(column = "vname", property = "villageId.name"),
			@Result(column = "dname", property = "villageId.district.name"),
			@Result(column = "pname", property = "villageId.district.province.name"),
			@Result(column = "specific_address", property = "specificAddress")
	}
	)
	List<Taddress> getAll();

	@Select("SELECT a.id as id,v.id vid, v.name as vname, d.name as dname, p.name as pname, specific_address " +
			"FROM Taddress a INNER JOIN Tvillages v ON a.village_id=v.id " +
			"INNER JOIN Tdistricts d ON v.district_id=d.id " +
			"INNER JOIN Tprovinces p ON d.province_id=p.id " +
			"WHERE a.id=#{id}")
	@ResultMap(value = "address")
	Taddress getById(@Param("id") Integer id);

	@Insert("INSERT INTO Taddress (specific_address,village_id) VALUES(#{addr.specificAddress},#{addr.villageId.id})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	int save(@Param("addr")Taddress address);
	
	
	@Update("UPDATE Taddress SET specific_address=#{addr.specificAddress},village_id=#{addr.villageId.id} WHERE id=#{addr.id}")
	int update(@Param("addr") Taddress address);

}
