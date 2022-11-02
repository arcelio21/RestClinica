package com.example.mapper.visit;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;
import com.example.entity.visit.Tvisit;

@Mapper
public interface MapperVisit {

	@Select("SELECT * FROM Tvisits")
	@Results(
		id = "visitId",
		value = {
			@Result(column = "id", property = "id"),
			@Result(column = "patient_id",property = "patientId.id"),
			@Result(column = "nurse_speciality_id", property = "nurseSpecialityId.id"),
			@Result(column = "physician_speciality_id", property = "physicianSpecialityId.id"),
			@Result(column = "speciality_id", property = "specialityId.id"),
			@Result(column = "observation", property = "observation"),
			@Result(column = "creation_date", property = "creation_date"),
			@Result(column = "status_id", property = "statusId.id")
		}
	)
	List<Tvisit> getAll();
	
	@ResultMap("visitId")
	@Select("SELECT * FROM Tvisits WHERE id=#{id}")
	Tvisit getById(@Param("id") Integer id);
	
	@ResultMap("visitId")
	@Select("SELECT * FROM Tvisits WHERE patient_id=#{pat.id} AND status_id=#{sts.id}")
	List<Tvisit> getByPatientId(@Param("pat") TuserTypeReg reg, @Param("sts") Tstatus tstatus);
	
	@ResultMap("visitId")
	@Select("SELECT * FROM Tvisits WHERE nurse_speciality_id=#{nurse.id} AND status_id=#{sts.id}")
	List<Tvisit> getByNurseId(@Param("nurse") TuserSpeciality tuserSpeciality, @Param("sts") Tstatus tstatus);
	
	
	@ResultMap("visitId")
	@Select("SELECT * FROM Tvisits WHERE physician_speciality_id=#{phy.id} AND status_id=#{sts.id}")
	List<Tvisit> getByPhysicianId(@Param("phy") TuserSpeciality speciality, @Param("sts") Tstatus tstatus);
	
	
	@ResultMap("visitId")
	@Select("SELECT * FROM Tvisits WHERE speciality_id=#{spec.id} AND status_id=#{sts.id}")
	List<Tvisit> getBySpecialityId(@Param("spec") Tspeciality tspeciality, @Param("sts") Tstatus tstatus);
	
	@ResultMap("visitId")
	@Select("SELECT * FROM Tvisits WHERE creation_date=#{date.id} AND status_id=#{sts.id}")
	List<Tvisit> getByCreationDate(@Param("sts") Tstatus tstatus, @Param("date") LocalDateTime date);
	
	@ResultMap("visitId")
	@Select("SELECT * FROM Tvisits WHERE status_id=#{sts.id}")
	List<Tvisit> getByStatusId(@Param("sts") Tstatus tstatus);
	
	@Update("UPDATE Tvisits "
			+ "SET patient_id=#{vis.patientId.id}, nurse_speciality_id=#{vis.nurseSpecialityId.id}, "
			+ " physician_speciality_id=#{vis.physicianSpecialityId.id}, speciality_id=#{vis.specialityId.id} "
			+ "observation=#{vis.observation},status_id=#{vis.statusId.id}"
			+ " WHERE id=#{vis.id}")
	Integer update (@Param("vis") Tvisit tvisit);
	
	@Insert("INSERT INTO Tvisits "
			+ "(patient_id,nurse_speciality_id,physician_speciality_id,speciality_id,observation,status_id) "
			+ "VALUES "
			+ "(#{vis.patientId.id},#{vis.nurseSpecialityId.id},#{vis.physicianSpecialityId.id},#{vis.specialityId.id},"
			+ "#{vis.observation},status_id=#{vis.statusId.id})")
	Integer save(@Param("vis") Tvisit tvisit);
	
	@Update("UPDATE Tvisits "
			+ "SET status_id=#{vis.statusId.id} "
			+ "WHERE id=#{vis.id}")
	Integer changeStatus(@Param("vis") Tvisit tvisit);
	
}
