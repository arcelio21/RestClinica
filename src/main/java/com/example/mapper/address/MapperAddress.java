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
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.annotations.One;

import com.example.entity.address.Taddress;

@Mapper
public interface MapperAddress {
	
	@Select("SELECT a.id as id,v.id vid, v.name as vname, d.name as dname, p.name as pname, specific_address " +
			"FROM Taddress a INNER JOIN Tvillages v ON a.village_id=v.id " +
			"INNER JOIN Tdistricts d ON v.district_id=d.id " +
			"INNER JOIN Tprovinces p ON d.province_id=p.id")
	@Results(
			id = "address", value = {
			@Result(column = "id",property = "id"),
			@Result(column = "vid", property = "villageId.id"),
			@Result(column = "vname",property = "villageId.name"),
			@Result(column = "dname", property = "villageId.district.name"),
			@Result(column = "pname",property = "villageId.district.province.name"),
			@Result(column = "specific_address", property = "specificAddress")
		}
	)
	public List<Taddress> getAll();
	
	@Select("SELECT * FROM Taddress WHERE id=#{id}")
	@Results(id = "addressAll",value={@Result(column = "id",property = "id"),
			@Result(column = "specific_address",property = "specificAddress"),
			@Result(column="village_id",property = "villageId",one = @One(select = "com.example.mapper.address.MapperVillage.getById",
					fetchType = FetchType.LAZY))}
	)
	public Taddress getByIdAll(@Param("id") Integer id);

	@Select("SELECT a.id as id,v.id vid, v.name as vname, d.name as dname, p.name as pname, specific_address " +
			"FROM Taddress a INNER JOIN Tvillages v ON a.village_id=v.id " +
			"INNER JOIN Tdistricts d ON v.district_id=d.id " +
			"INNER JOIN Tprovinces p ON d.province_id=p.id " +
			"WHERE a.id=#{id}")
	@ResultMap(value = "address")
	public Taddress getById(@Param("id") Integer id);
	
	
	@Select("SELECT a.id as id,a.specific_address as 'specific',v.id as villId,v.name as villName FROM Taddress a "
			+ "INNER JOIN Tvillages v  ON a.village_id=v.id WHERE a.id=#{id}")
	@Result(column = "id",property = "id")
	@Result(column = "specific",property = "specificAddress")
	@Result(column = "villId",property = "villageId.id")
	@Result(column = "villName",property = "villageId.name")
	public Taddress getVillageAllById(@Param("id") Integer id);
	
	
	@Insert("INSERT INTO Taddress (specific_address,village_id) VALUES(#{addr.specificAddress},#{addr.villageId.id})")
	public int save(@Param("addr")Taddress address);
	
	
	@Update("UPDATE Tvillages SET specific_address=#{addr.specificAddress},village_id=#{addr.villageId.id} WHERE id=#{addr.id}")
	public int update(@Param("addr") Taddress address);

}
