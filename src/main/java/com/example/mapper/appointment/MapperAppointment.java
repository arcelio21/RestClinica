package com.example.mapper.appointment;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.appointment.Tappointment;
import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;

@Mapper
public interface MapperAppointment {

	@Select("SELECT * FROM Tappointment")
	@Results(
		id = "appointRS",
		value = {
			@Result(column = "id",property = "id"),
			@Result(column = "patient_id",property = "patientId.id"),
			@Result(column = "speciality_id",property = "specialityId.id"),
			@Result(column = "secretary_id",property = "secretaryId.id"),
			@Result(column = "speciality_physician_id",property = "specialityPhysicianId.id"),
			@Result(column = "observation",property = "observation"),
			@Result(column = "creation_date",property = "creationDate"),
			@Result(column = "visit_date", property = "visitDate"),
			@Result(column = "status_id", property = "statusId.id")
		}
	)
	List<Tappointment> getAll();

	
	@ResultMap(value = "appointRS")
	@Select("SELECT * FROM Tappointment WHERE id=#{id}")
	Tappointment getById(@Param("id") Integer id);
	
	@ResultMap(value = "appointRS")
	@Select("SELECT * FROM Tappointment WHERE patient_id=#{ptn.id}  AND status_id=#{st.id}")
	List<Tappointment> getByPatientId(@Param("ptn") TuserTypeReg tuserTypeReg, @Param("st") Tstatus tstatus );
	
	@ResultMap(value = "appointRS")
	@Select("SELECT * FROM Tappointment WHERE speciality_id=#{spc.id} AND status_id=#{st.id}")
	List<Tappointment> getBydSpecialityId(@Param("spc") Tspeciality tspeciality, @Param("st") Tstatus tstatus);
	
	
	@ResultMap(value = "appointRS")
	@Select("SELECT * FROM Tappointment WHERE secretary_id=#{scr.id} AND status_id=#{st.id}")
	List<Tappointment> getBySecretaryId(@Param("scr") TuserTypeReg tuserTypeReg, @Param("st") Tstatus tstatus);
	
	
	@ResultMap(value = "appointRS")
	@Select("SELECT * FROM Tappointment WHERE speciality_physician_id=#{usp.id} AND status_id=#{st.id}")
	List<Tappointment> getBySpecialityPhysicianId(@Param("usp") TuserSpeciality tuserSpeciality, @Param("st") Tstatus tstatus);
	
	@ResultMap(value = "appointRS")
	@Select("SELECT * FROM Tappointment WHERE creation_date=#{date} AND status_id=#{st.id}")
	List<Tappointment> getByCreationDate(@Param("date") LocalDateTime dateTime, @Param("st") Tstatus tstatus);
	
	@ResultMap(value = "appointRS")
	@Select("SELECT * FROM Tappointment WHERE visit_date=#{date} AND status_id=#{st.id}")
	List<Tappointment> getByVisitDate(@Param("date") LocalDateTime dateTime, @Param("st") Tstatus tstatus);
	
	@ResultMap(value = "appointRS")
	@Select("SELECT * FROM Tappointment WHERE  AND status_id=#{st.id}")
	List<Tappointment> getByStatusId( @Param("st") Tstatus tstatus);
	
	@Update("UPDATE Tappointment "
			+ "SET "
			+ "patient_id=#{apoint.patientId.id}, speciality_id=#{apoint.specialityId.id},"
			+ "secretary_id=#{apoint.secretaryId.id}, speciality_physician_id=#{apoint.specialityPhysicianId.id} "
			+ "observation=#{apoint.observation}, visit_date=#{apoint.visitDate},"
			+ "status_id=#{apoint.statusId.id} "
			+ "WHERE id=#{id}")
	Integer update(@Param("apoint") Tappointment tappointment);
	
	@Insert("INSERT INTO Tappointment "
			+ "(patient_id,speciality_id,secretary_id,speciality_physician_id,observation,visit_date,status_id) "
			+ "VALUES(#{apoint.patientId.id}, #{apoint.specialityId.id}, #{apoint.secretaryId.id}, #{apoint.specialityPhysicianId.id}, #{apoint.observation}"
			+ ", #{apoint.visitDate}, #{apoint.statusId.id})")
	Integer save(@Param("apoint") Tappointment tappointment);
	
	
	@Update("UPDATE Tappointment "
			+ "SET status_id=#{apoint.statusId.id} "
			+ "WHERE id=#{apoint.id}")
	Integer changeStatus(@Param("apoint") Tappointment tappointment);
	
	
	
}
