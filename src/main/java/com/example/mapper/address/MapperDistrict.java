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

	@Select("SELECT id,name FROM Tdistricts")
	@Result(column = "id", property = "id")
	@Result(column = "name", property = "name")
	public List<Tdistrict> getALl();

	@Select("SELECT id,name FROM Tdistricts WHERE id=#{id}")
	@Result(column = "id", property = "id")
	@Result(column = "name", property = "name")
	public Tdistrict getById(@Param("id") Integer id);
	@Select("SELECT * FROM Tdistricts")
	@Results(id = "districtAll",
			value={@Result(column = "id",property = "id"),
			@Result(column = "name",property = "name"),
			@Result(column="province_Id",property = "province",one = @One(select = "com.example.mapper.address.MapperProvince.getByIdSimple",
					fetchType = FetchType.LAZY)),
			@Result(column = "id", property = "villages", many = @Many(select = "com.example.mapper.address.MapperVillage.getByDistrictId",fetchType = FetchType.LAZY ))}
	)
	public List<Tdistrict> getAllExtra();
	
	/**
	 * METODO QUE RETURNA DISTRITOS POR ID, JUNTO A SU PROVINCIA Y CORREGIMIENTOS ASOCIADOS
	 * @param id (Integer) DEL DISTRITO A BUSCAR
	 * @return UN OBJETO 'Tdistrict'
	 */
	@Select("SELECT * FROM Tdistricts WHERE id=#{id}")
	@ResultMap(value = "districtAll")
	public Tdistrict getByIdAll(@Param("id") Integer id);
	
	/**
	 * METODO QUE RETORNA LA INFORMACION DE  DISTRITOS POR ID Y SU PROVINCIA ASOCIADA
	 * @param id (Integer) DEL DISTRITO A BUSCAR
	 * @return UN OBJETO 'Tdistrict'
	 */
	@Select("SELECT * FROM Tdistricts WHERE id=#{id}")
	@Results(id = "districtSimple",
		value = {
			@Result(column = "id", property = "id"),
			@Result(column = "name", property = "name"),
			@Result(column="province_Id",property = "province",one = @One(select = "com.example.mapper.address.MapperProvince.getByIdSimple",
			fetchType = FetchType.LAZY))
		}
	)
	public Tdistrict getProvinceByIdDistrict(@Param("id") Integer id);
	
	@Select("SELECT * FROM Tdistricts WHERE province_Id=#{prov.id}")
	@Result(column = "id", property = "id")
	@Result(column = "name", property = "name")
	@Result(column = "id", property = "villages", many = @Many(select = "com.example.mapper.address.MapperVillage.getByDistrictId",fetchType = FetchType.LAZY ))
	List<Tdistrict> getByProvinceId(@Param("prov") Tprovince tprovince);
	
	
	@Select("SELECT Tdistricts.id,Tdistricts.name,Tprovinces.id as provId,Tprovinces.name as provName FROM Tdistricts  "
			+ "INNER JOIN Tprovinces  ON Tdistricts.province_id=Tprovinces.id WHERE Tdistricts.id=#{id}")
	@Results(id="districtAllSimple",value=
			{@Result(column = "id",property = "id"),
			@Result(column = "name",property = "name"),
			@Result(column = "provId",property = "province.id"),
			@Result(column = "provName",property = "province.name")}
	)
	public Tdistrict getDistrictAllSimpleById(@Param("id") Integer id);
	
	
	@Insert("INSERT INTO Tdistricts (name) VALUES(#{dist.name})")
	public int save(@Param("dist")Tdistrict district);
	
	@Update("UPDATE Tdistricts SET name=#{dist.name} WHERE id=#{dist.id}")
	public int update(@Param("dist") Tdistrict district);
	
	
}
