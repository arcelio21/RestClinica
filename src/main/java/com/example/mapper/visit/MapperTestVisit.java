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

import com.example.entity.visit.Ttest;
import com.example.entity.visit.TtestVisit;
import com.example.entity.visit.Tvisit;

@Mapper
public interface MapperTestVisit {

	@Select("SELECT * FROM Ttests_visits")
	@Results(
		id = "testVisId",
		value = {
			@Result(column = "id",property = "id"),
			@Result(column = "visit_id",property = "visitId.id"),
			@Result(column = "test_id", property = "testId.id"),
			@Result(column = "observation", property = "observation")
		}
	)
	List<TtestVisit> getAll();
	
	@ResultMap("testVisId")
	@Select("SELECT * FROM Ttests_visits WHERE id=#{id}")
	TtestVisit getById(@Param("id") Integer id);
	
	@ResultMap("testVisId")
	@Select("SELECT * FROM Ttests_visits WHERE visit_id=#{vis.id}")
	List<TtestVisit> getByVisitId(@Param("vis") Tvisit tvisit);
	
	@ResultMap("testVisId")
	@Select("SELECT * FROM Ttests_visits WHERE vital_sign_id=#{test.id}")
	List<TtestVisit> getByTestId(@Param("test") Ttest ttest);
	
	
	@Update("UPDATE Ttests_visits "
			+ "SET visit_id=#{vis.visitId.id}, test_id=#{vis.testId.id},observation=#{vis.observation} "
			+ "WHERE id=#{vis.id}")
	Integer update(@Param("vis") TtestVisit ttestVisit);
	
	@Insert("INSERT INTO Ttests_visits "
			+ "(visit_id,test_id,observation) "
			+ "VALUES (#{vis.visitId.id},#{vis.testId.id},#{vis.observation})")
	Integer save(@Param("vis") TtestVisit ttestVisit);
}
