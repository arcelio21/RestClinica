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

import com.example.entity.visit.Tdiagnose;
import com.example.entity.visit.TdiagnoseVisit;
import com.example.entity.visit.Tvisit;

@Mapper
public interface MapperDiagnosesVisit {

	@Select("SELECT * FROM Tdiagnoses_visits")
	@Results(
		id = "diagVisit",
		value = {
			@Result(column = "id", property = "id"),
			@Result(column = "visit_id", property = "visitId.id"),
			@Result(column = "diagnosis_id", property = "diagnoseId.id"),
			@Result(column = "observation", property = "observation")
		}
	)
	List<TdiagnoseVisit> getAll();
	
	@ResultMap("diagVisit")
	@Select("SELECT * FROM Tdiagnoses_visits WHERE id=#{id}")
	TdiagnoseVisit getById(@Param("id") Integer id);
	
	@ResultMap("diagVisit")
	@Select("SELECT * FROM Tdiagnoses_visits WHERE visit_id=#{vis.id}")
	List<TdiagnoseVisit> getByVisitId(@Param("vis") Tvisit tvisit);
	
	@ResultMap("diagVisit")
	@Select("SELECT * FROM Tdiagnoses_visits WHERE diagnosis_id=#{diag.id}")
	List<TdiagnoseVisit> getByDiagnosesId(@Param("diag") Tdiagnose tdiagnose);
	
	@ResultMap("diagVisit")
	@Update("UPDATE Tdiagnoses_visits "
			+ "SET visit_id=#{diagVis.visitId.id}, diagnosis_id=#{diagVis.diagnoseId.id}, observation=#{diagVis.observation} "
			+ "WHERE id=#{diagVis.id}")
	Integer update(@Param("diagVis") TdiagnoseVisit tdiagnoseVisit);
	
	@Insert("INSERT INTO Tdiagnoses_visits "
			+ "(visit_id,diagnosis_id,observation) "
			+ "VALUES (#{diagVis.visitId.id},#{diagVis.diagnoseId.id},#{diagVis.observation})")
	Integer save(@Param("diagVis") TdiagnoseVisit tdiagnoseVisit);
}
