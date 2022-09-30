package com.example.mapper.address;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import com.example.entity.address.Tprovince;

@Mapper
@Repository
public interface MapperProvince {

	@Select(value = "SELECT * FROM Tprovinces")
	public List<Tprovince> getAllSimple();
	
	@Select(value = "SELECT * FROM Tprovinces")
	@Results(
		id="provRe",
		value= {
			@Result(column = "id", property = "id"),
			@Result(column = "name", property = "name"),
			@Result(column = "id", property = "districts",many = @Many(select = "com.example.mapper.address.MapperDistrict.getByProvinceId"
			,fetchType = FetchType.LAZY))
		}
	)
	List<Tprovince> getAll();
	
	@ResultMap("provRe")
	@Select("SELECT * FROM Tprovinces WHERE id=#{id}")
	public Tprovince getById(@Param("id") Integer id);
	
	@Select("SELECT id,name FROM Tprovinces WHERE id=#{id}")
	@Results(
		id = "provinceSimple",
		value = {
			@Result(column = "id",property = "id"),
			@Result(column = "name", property = "name")
		}
	)
	public Tprovince getByIdSimple(@Param("id") Integer id);
	
	@Insert("INSERT INTO Tprovinces (name)values(#{prov.name})")
	public int save(@Param("prov")Tprovince province);
	
	@Update("UPDATE Tprovinces SET name=#{prov.name} WHERE id=#{prov.id}")
	public int update(@Param("prov") Tprovince province);
	
	/*
	 * @Select("SELECT id,name FROM Tprovinces  WHERE id=#{id}")
	 * 
	 * @Results( value = {
	 * 
	 * @Result(column = "p.id",property = "id"),
	 * 
	 * @Result(column = "p.name",property = "name"),
	 * 
	 * @Result(property="districts", column="province_id", javaType= List.class,
	 * many=@Many(select = "getByProvinces")) } ) public List<Tprovince>
	 * getAllProvincesById(@Param("id") Integer id);
	 * 
	 * @Select(
	 * value="SELECT id,name FROM Tdistricts WHERE province_id=#{idProv}",databaseId
	 * = "getByProvinces")
	 * 
	 * @Results(value={@Result(column = "id",property = "id"),
	 * 
	 * @Result(column = "name",property = "name")} ) public List<Tdistrict>
	 * getByProvince(@Param("idProv")Integer id);
	 */
	
	
	
}
