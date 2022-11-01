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

import com.example.entity.visit.Tsymptom;
import com.example.entity.visit.TsymptomVisit;
import com.example.entity.visit.Tvisit;

@Mapper
public interface MapperSymptomVisit {

	@Results(
		id = "symVis",
		value = {
			@Result(column = "id", property = "id"),
			@Result(column = "visit_id", property = "visitId.id"),
			@Result(column = "symptoms_id", property = "symptomId.id"),
			@Result(column = "observation", property = "observation")
		}
	)
	@Select("SELECT * FROM Tsymptoms_visits")
	List<TsymptomVisit> getAll();
	
	@ResultMap("symVis")
	@Select("SELECT * FROM Tsymptoms_visits WHERE id=#{id}")
	TsymptomVisit getById(@Param("id") Integer id);
	
	@ResultMap("symVis")
	@Select("SELECT * FROM Tsymptoms_visits WHERE visit_id=#{vis.id}")
	List<TsymptomVisit> getByVisitId(@Param("vis") Tvisit tvisit);
	
	@ResultMap("symVis")
	@Select("SELECT * FROM Tsymptoms_visits WHERE symptoms_id=#{sym.id}")
	List<TsymptomVisit> getBySymptomId(@Param("sym") Tsymptom tsymptom);
	
	@Update("UPDATE Tsymptoms_visits "
			+ "SET visit_id=#{symVis.visitId.id}, symptoms_id=#{symVis.symptomId.id}, obervation=#{symVis.observation} "
			+ "WHERE id=#{symVis.id}")
	Integer update(@Param("symVis") TsymptomVisit tsymptomVisit);
	
	@Insert("INSERT INTO Tsymptoms_visits "
			+ "(visit_id,symptoms_id,obervation) "
			+ "VALUES (#{symVis.visitId.id},#{symVis.symptomId.id},#{symVis.observation})")
	Integer save(@Param("symVis") TsymptomVisit tsymptomVisit);
}
