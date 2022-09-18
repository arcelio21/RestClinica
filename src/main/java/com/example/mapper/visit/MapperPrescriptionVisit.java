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

import com.example.entity.visit.Tprescription;
import com.example.entity.visit.TprescriptionVisit;
import com.example.entity.visit.Tvisit;

@Mapper
public interface MapperPrescriptionVisit {

	@Select("SELECT * FROM Tprescriptions_visits")
	@Results(
		id = "preVisId",
		value = {
			@Result(column = "id",property = "id"),
			@Result(column = "visit_id",property = "visitId.id"),
			@Result(column = "prescription_id", property = "prescriptionId.id"),
			@Result(column = "observation", property = "observation")
		}
	)
	List<TprescriptionVisit> getAll();
	
	@ResultMap("preVisId")
	@Select("SELECT * FROM Tprescriptions_visits WHERE id=#{id}")
	TprescriptionVisit getById(@Param("id") Integer id);
	
	@ResultMap("preVisId")
	@Select("SELECT * FROM Tprescriptions_visits WHERE visit_id=#{vis.id}")
	List<TprescriptionVisit> getByVisitId(@Param("vis") Tvisit tvisit);
	
	@ResultMap("preVisId")
	@Select("SELECT * FROM Tprescriptions_visits WHERE prescription_id=#{pre.prescriptionId.id}")
	List<TprescriptionVisit> getByPrescriptionId(@Param("pre") Tprescription tprescription);
	
	@Update(
		"UPDATE Tprescriptions_visits "
		+ "SET visit_id=#{preVis.visitId.id},prescription_id=#{preVis.prescriptionId.id},obervation=#{preVis.observation} "
		+ "WHERE id=#{preVis.id}"
	)
	Integer update(@Param("preVis") TprescriptionVisit tprescriptionVisit);
	
	
	@Insert("INSERT INTO Tprescriptions_visits "
			+ "(visit_id,prescription_id,obervation) "
			+ "VALUES (#{preVis.visitId.id},#{preVis.prescriptionId.id},#{preVis.observation})")
	Integer save(@Param("preVis") TprescriptionVisit tprescriptionVisit);
}
