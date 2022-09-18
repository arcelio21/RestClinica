package com.example.mapper.visit;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.visit.Tvisit;
import com.example.entity.visit.TvitalSign;
import com.example.entity.visit.TvitalSignVisit;

@Mapper
public interface MapperVitalSignVisit {

	@Select("SELECT * FROM Tvital_signs_visits")
	@Results(
		id = "vitalVisId",
		value = {
			@Result(column = "id",property = "id"),
			@Result(column = "visit_id",property = "visitId.id"),
			@Result(column = "vital_sign_id", property = "vitalSignId.id"),
			@Result(column = "observation", property = "observation")
		}
	)
	List<TvitalSignVisit> getAll();
	
	@ResultMap("vitalVisId")
	@Select("SELECT * FROM Tvital_signs_visits WHERE id=#{id}")
	TvitalSignVisit getById(@Param("id") Integer id);
	
	@ResultMap("vitalVisId")
	@Select("SELECT * FROM Tvital_signs_visits WHERE visit_id=#{vis.id}")
	List<TvitalSignVisit> getByVisitId(@Param("vis") Tvisit tvisit);
	
	@ResultMap("vitalVisId")
	@Select("SELECT * FROM Tvital_signs_visits WHERE vital_sign_id=#{vital.id}")
	List<TvitalSignVisit> getByVitalSignId(@Param("vital") TvitalSign sign);
	
	
	@Update("UPDATE Tvital_signs_visits "
			+ "SET visit_id=#{vis.visitId.id}, vital_sign_id=#{vis.vitalSignId.id},observation=#{vis.observation} "
			+ "WHERE id=#{vis.id}")
	Integer update(@Param("vis") TvitalSignVisit signVisit);
	
	@Insert("INSERT INTO Tvital_signs_visits "
			+ "(visit_id,vital_sign_id,observation) "
			+ "VALUES (#{vis.visitId.id},#{vis.vitalSignId.id},#{vis.observation})")
	Integer save(@Param("vis") TvitalSignVisit signVisit);
}
