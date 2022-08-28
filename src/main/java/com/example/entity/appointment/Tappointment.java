package com.example.entity.appointment;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;

public class Tappointment {
	
	@Positive
	private Integer id;
	
	@NotNull
	private TuserTypeReg patientId;
	
	@NotNull
	private Tspeciality specialityId;
	
	@NotNull
	private TuserTypeReg secretaryId;
	
	@NotNull
	private TuserSpeciality specialityPhysicianId;
	
	@Max(50)
	private String observation;
	
	@FutureOrPresent
	@NotNull
	private LocalDateTime creationDate;
	
	@FutureOrPresent
	@NotNull
	private LocalDateTime visitDate;
	
	@NotNull
	private Tstatus statusId;

	
	public Tappointment() {
		super();
	}


	public Tappointment(@Positive Integer id) {
		super();
		this.id = id;
	}


	public Tappointment(@FutureOrPresent @NotNull LocalDateTime creationDate) {
		super();
		this.creationDate = creationDate;
	}


	public Tappointment(@NotNull  TuserTypeReg patientId) {
		super();
		this.patientId = patientId;
	}


	public Tappointment(@Positive Integer id, @NotNull  TuserTypeReg patientId,
			@NotNull  Tspeciality specialityId, @NotNull  TuserTypeReg secretaryId,
			@NotNull  TuserSpeciality specialityPhysicianId, @Max(50) String observation,
			@FutureOrPresent @NotNull LocalDateTime creationDate, @FutureOrPresent @NotNull LocalDateTime visitDate,
			@NotNull Tstatus statusId) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.specialityId = specialityId;
		this.secretaryId = secretaryId;
		this.specialityPhysicianId = specialityPhysicianId;
		this.observation = observation;
		this.creationDate = creationDate;
		this.visitDate = visitDate;
		this.statusId = statusId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public TuserTypeReg getPatientId() {
		return patientId;
	}


	public void setPatientId(TuserTypeReg patientId) {
		this.patientId = patientId;
	}


	public Tspeciality getSpecialityId() {
		return specialityId;
	}


	public void setSpecialityId(Tspeciality specialityId) {
		this.specialityId = specialityId;
	}


	public TuserTypeReg getSecretaryId() {
		return secretaryId;
	}


	public void setSecretaryId(TuserTypeReg secretaryId) {
		this.secretaryId = secretaryId;
	}


	public TuserSpeciality getSpecialityPhysicianId() {
		return specialityPhysicianId;
	}


	public void setSpecialityPhysicianId(TuserSpeciality specialityPhysicianId) {
		this.specialityPhysicianId = specialityPhysicianId;
	}


	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}


	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}


	public LocalDateTime getVisitDate() {
		return visitDate;
	}


	public void setVisitDate(LocalDateTime visitDate) {
		this.visitDate = visitDate;
	}


	public Tstatus getStatusId() {
		return statusId;
	}


	public void setStatusId(Tstatus statusId) {
		this.statusId = statusId;
	}


	@Override
	public String toString() {
		return "Tappointment [id=" + id + ", patientId=" + patientId.getId() + ", specialityId=" + specialityId.getId()
				+ ", secretaryId=" + secretaryId.getId() + ", specialityPhysicianId=" + specialityPhysicianId.getId() + ", observation="
				+ observation + ", creationDate=" + creationDate + ", visitDate=" + visitDate + ", statusId=" + statusId.getId()
				+ "]";
	}
	
	

}
